package com.supplychain.supply_chain_api.dto;

import java.io.Serializable;
import com.supplychain.supply_chain_api.domain.enums.EntregaStatus;

public record EventoPacoteDTO (
    String codigoRastreio,
    EntregaStatus status
) implements Serializable{}
