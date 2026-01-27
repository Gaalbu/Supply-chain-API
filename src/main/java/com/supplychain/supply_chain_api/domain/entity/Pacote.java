package com.supplychain.supply_chain_api.domain.entity;

// IMPORTANTE: Importar o novo Enum com nome em português
import com.supplychain.supply_chain_api.domain.enums.EntregaStatus;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pacotes") 
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pacote { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoRastreio; 

    @Column(nullable = false)
    private String destinatario; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntregaStatus status; 

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao; 

    @UpdateTimestamp
    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao; 
}