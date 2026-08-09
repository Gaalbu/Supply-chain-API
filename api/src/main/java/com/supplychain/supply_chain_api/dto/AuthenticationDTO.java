package com.supplychain.supply_chain_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank String login,
        @NotBlank String password
) {}
