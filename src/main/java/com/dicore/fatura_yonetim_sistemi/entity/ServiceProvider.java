package com.dicore.fatura_yonetim_sistemi.entity;

import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @Column
    private String contactInfo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;
}
