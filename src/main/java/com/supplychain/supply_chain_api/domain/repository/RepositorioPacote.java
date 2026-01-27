package com.supplychain.supply_chain_api.domain.repository;

import com.supplychain.supply_chain_api.domain.entity.Pacote; // Importa Pacote
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RepositorioPacote extends JpaRepository<Pacote, Long> {
    
    Optional<Pacote> findByCodigoRastreio(String codigoRastreio);
}