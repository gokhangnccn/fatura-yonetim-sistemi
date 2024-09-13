package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.request.BillRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.BillResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import com.dicore.fatura_yonetim_sistemi.entity.User;

import java.math.BigDecimal;

public class BillMapper {

    public static Bill toEntity(BillRequestDTO billRequestDTO, User user, ServiceProvider serviceProvider) {
        Bill bill = new Bill();
        bill.setDueDate(billRequestDTO.getDueDate());
        bill.setStatus(billRequestDTO.getStatus());
        bill.setBillingUnit(serviceProvider.getServiceType().getBillingUnit());
        bill.setUnitUsage(billRequestDTO.getUnitUsage());

        bill.setUser(user);
        bill.setServiceProvider(serviceProvider);

        // kullanılan servisin billing unitine göre tutar hesaplama
        BigDecimal unitPrice = serviceProvider.getUnitPrice();
        BigDecimal amount = billRequestDTO.getUnitUsage().multiply(unitPrice);
        bill.setAmount(amount);

        return bill;
    }

    public static BillResponseDTO toResponseDTO(Bill bill) {
        BillResponseDTO dto = new BillResponseDTO();
        dto.setId(bill.getId());
        dto.setDueDate(bill.getDueDate());
        dto.setPaidDate(bill.getPaidDate());
        dto.setStatus(bill.getStatus());
        dto.setBillingUnit(bill.getBillingUnit());
        dto.setUnitUsage(bill.getUnitUsage());
        dto.setAmount(bill.getAmount());
        dto.setUserName(bill.getUser().getName());
        dto.setServiceProviderName(bill.getServiceProvider().getName());
        return dto;
    }
}
