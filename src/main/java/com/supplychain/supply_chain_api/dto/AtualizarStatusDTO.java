package com.supplychain.supply_chain_api.dto;

import com.supplychain.supply_chain_api.domain.enums.EntregaStatus;
import jakarta.validation.constraints.NotNull;

//Record feito apenas para gerir a alteração de status
public record AtualizarStatusDTO (
    @NotNull(message = "O novo status é obrigatório")
    EntregaStatus novoStatus
) {}
