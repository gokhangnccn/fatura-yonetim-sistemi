package com.dicore.fatura_yonetim_sistemi.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceResponseDTO {

    private Long id;

    private String filePath;

    private Long billId;
}

