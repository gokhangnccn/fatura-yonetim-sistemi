package com.dicore.fatura_yonetim_sistemi.dtos.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class InvoiceRequestDTO {

    @NotBlank(message = "File path is required")
    private String filePath;

    @NotNull(message = "Bill ID is required")
    private Long billId;
}
