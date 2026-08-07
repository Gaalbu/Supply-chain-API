package com.supplychain.supply_chain_api.controller;

import com.supplychain.supply_chain_api.domain.entity.Pacote;
import com.supplychain.supply_chain_api.dto.AtualizarStatusDTO;
import com.supplychain.supply_chain_api.dto.CriarPacoteDTO;
import com.supplychain.supply_chain_api.service.ServicoPacote;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacotes")
@RequiredArgsConstructor
public class ControladorPacote{
    private final ServicoPacote servico;

    //endpoint p criar
    @PostMapping
    public ResponseEntity<Pacote> criarPacote(@RequestBody @Valid CriarPacoteDTO dados){
        Pacote pacoteCriado = servico.criar(dados);
        return new ResponseEntity<>(pacoteCriado, HttpStatus.CREATED);
    }

    @PatchMapping("/{codigoRastreio}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable String codigoRastreio, @RequestBody @Valid AtualizarStatusDTO dados) {
        servico.atualizarStatus(codigoRastreio, dados.novoStatus());
        return ResponseEntity.noContent().build();
    }
}