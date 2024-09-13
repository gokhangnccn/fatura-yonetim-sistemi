package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.request.InvoiceRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.InvoiceResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.Invoice;

public class InvoiceMapper {

    public static Invoice toEntity(InvoiceRequestDTO dto, Bill bill) {
        Invoice invoice = new Invoice();
        invoice.setFilePath(dto.getFilePath());
        invoice.setBill(bill);
        return invoice;
    }

    public static InvoiceResponseDTO toResponseDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setFilePath(invoice.getFilePath());
        dto.setBillId(invoice.getBill().getId());
        return dto;
    }
}

