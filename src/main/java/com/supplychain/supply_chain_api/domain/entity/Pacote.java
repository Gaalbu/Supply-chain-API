package com.supplychain.supply_chain_api.domain.entity;

import com.supplychain.supply_chain_api.domain.enums.EntregaStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_packages")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String trackingCode;

    @Column(nullable = false)
    private String recipientName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntregaStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime lastUpdate;
}