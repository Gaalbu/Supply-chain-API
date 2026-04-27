package com.supplychain.supply_chain_api.domain.user;

public enum UserRole{
    ADMIN("admin"),
    OPERADOR("operador");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}