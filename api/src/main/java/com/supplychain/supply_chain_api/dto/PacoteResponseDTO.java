package com.supplychain.supply_chain_api.dto;

import com.supplychain.supply_chain_api.domain.entity.Pacote;
import com.supplychain.supply_chain_api.domain.enums.EntregaStatus;

import java.time.LocalDateTime;

public record PacoteResponseDTO(
        Long id,
        String codigo,
        String destinatario,
        EntregaStatus status,
        LocalDateTime dataAtualizacao
) {
    public static PacoteResponseDTO from(Pacote pacote) {
        return new PacoteResponseDTO(
                pacote.getId(),
                pacote.getCodigoRastreio(),
                pacote.getDestinatario(),
                pacote.getStatus(),
                pacote.getUltimaAtualizacao() != null ? pacote.getUltimaAtualizacao() : pacote.getDataCriacao()
        );
    }
}
