package com.supplychain.supply_chain_api.domain.repository;

import com.supplychain.supply_chain_api.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    UserDetails findByLogin(String login);
}
