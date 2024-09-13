package com.dicore.fatura_yonetim_sistemi.dtos.request;

import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServiceProviderRequestDTO {

    @NotBlank(message = "Service provider name cannot be blank")
    @Size(min = 2, max = 50, message = "Service provider name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Service type cannot be null")
    private ServiceType serviceType;

    private String contactInfo;

    @NotNull(message = "Unit price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Unit price must be a valid")
    private BigDecimal unitPrice;
}