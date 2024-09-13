package com.dicore.fatura_yonetim_sistemi.dtos.response;

import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceProviderResponseDTO {

    private Long id;

    private String name;

    private ServiceType serviceType;

    private String contactInfo;

    private BigDecimal unitPrice;
}