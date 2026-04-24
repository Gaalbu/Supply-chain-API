package com.supplychain.supply_chain_api.event;

import com.supplychain.supply_chain_api.config.ConfiguracaoRabbitMQ;
import com.supplychain.supply_chain_api.dto.EventoPacoteDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OuvintePacote {

    @RabbitListener(queues = ConfiguracaoRabbitMQ.NOME_FILA)
    public void receberNotificacao(EventoPacoteDTO evento) {

        System.out.println("=======================================");
        System.out.println("[RABBITMQ] NOVA MENSAGEM RECEBIDA!");
        System.out.println("Pacote: " + evento.codigoRastreio());
        System.out.println("Novo Status: " + evento.status());
        System.out.println("Simulando envio de e-mail para o cliente...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("E-mail enviado com sucesso!");
        System.out.println("=======================================");
    }
}