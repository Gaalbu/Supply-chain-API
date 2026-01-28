package com.supplychain.supply_chain_api.dto;

import jakarta.validation.constraints.NotBlank;

// ao usar record, já temos getters, constructors, equals e toString
public record CriarPacoteDTO (
    @NotBlank(message = "O destinatário é obrigatório")
    String destinatario,

    @NotBlank(message = "O código de rastreio é obrigatório")
    String codigoRastreio
){}
