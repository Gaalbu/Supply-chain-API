package com.supplychain.supply_chain_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank @Size(max = 120) String login,
        @NotBlank @Size(min = 8, max = 72) String password
) {
}
