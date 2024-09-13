package com.dicore.fatura_yonetim_sistemi.dtos.request;

import com.dicore.fatura_yonetim_sistemi.enums.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDTO {

    @NotNull(message = "Bill ID cannot be null")
    private Long billId;

    @NotNull(message = "Amount paid cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount paid must be greater than zero")
    private BigDecimal amountPaid;

    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;
}
