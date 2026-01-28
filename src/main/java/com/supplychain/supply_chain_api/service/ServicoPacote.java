package com.supplychain.supply_chain_api.service;

import com.supplychain.supply_chain_api.domain.entity.Pacote;
import com.supplychain.supply_chain_api.domain.enums.EntregaStatus;
import com.supplychain.supply_chain_api.domain.repository.RepositorioPacote;
import com.supplychain.supply_chain_api.dto.CriarPacoteDTO;
import com.supplychain.supply_chain_api.dto.EventoPacoteDTO;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service //classe de regra de negócio (Spring entra aqui)
@RequiredArgsConstructor // construtor é criado para injetar as dependências
public class ServicoPacote {
    
    private final RepositorioPacote repositorio;
    private final RabbitTemplate rabbitTemplate; // RabbitMQ entra aqui

    //Create
    @Transactional
    public Pacote criar(CriarPacoteDTO dados){
        Pacote pacote = Pacote.builder()
            .codigoRastreio(dados.codigoRastreio())
            .destinatario(dados.destinatario())
            .status(EntregaStatus.PREPARACAO) //default
            .build();
        
        return repositorio.save(pacote);
    }

    //Update
    @Transactional
    public void atualizarStatus(String codigoRastreio, EntregaStatus novoStatus){
        // Busca pacote no banco. Lança erro se não achar.
        Pacote pacote = repositorio.findByCodigoRastreio(codigoRastreio)
                            .orElseThrow(() -> new RuntimeException("Pacote não encontrado com o código: " + codigoRastreio));
        
        // Não troca o status de algo já entregue
        if (EntregaStatus.ENTREGUE.equals(pacote.getStatus())) {
            throw new IllegalStateException("Pacote já foi entregue, portanto seu status não pode ser alterado.");
        }

        pacote.setStatus(novoStatus);
        repositorio.save(pacote);

        //Envia uma msg para o rabbit, usando o nosso dto de evento
        EventoPacoteDTO evento = new EventoPacoteDTO(pacote.getCodigoRastreio(), novoStatus);

        // "supply-chain.exchange" é o nome do canal de comunicação
        // "pacote.atualizado" é a chave de roteamento (assunto)
        rabbitTemplate.convertAndSend("supply-chain.exchange", "pacote.atualizado", evento);
    }
}
