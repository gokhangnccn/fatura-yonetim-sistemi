package com.dicore.fatura_yonetim_sistemi.dtos.request;

import com.dicore.fatura_yonetim_sistemi.enums.BillStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BillRequestDTO {

    @NotNull(message = "Due date cannot be null")
    private LocalDate dueDate;

    @NotNull(message = "Status cannot be null")
    private BillStatus status;

    @NotNull(message = "Unit usage cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit usage must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Unit usage must be a valid")
    private BigDecimal unitUsage;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Service Provider ID cannot be null")
    private Long serviceProviderId;
}

