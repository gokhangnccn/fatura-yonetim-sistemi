package com.dicore.fatura_yonetim_sistemi.dtos.response;

import com.dicore.fatura_yonetim_sistemi.enums.BillStatus;
import com.dicore.fatura_yonetim_sistemi.enums.BillingUnit;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BillResponseDTO {

    private Long id;

    private LocalDate dueDate;

    private LocalDate paidDate;

    private BillStatus status;

    private BillingUnit billingUnit;

    private BigDecimal unitUsage;

    private BigDecimal amount;

    private String serviceProviderName;

    private String userName;
}

