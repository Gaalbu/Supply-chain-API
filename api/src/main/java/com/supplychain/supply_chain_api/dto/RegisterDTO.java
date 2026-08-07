package com.supplychain.supply_chain_api.dto;

import com.supplychain.supply_chain_api.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
