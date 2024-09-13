package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.request.PaymentRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.PaymentResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.Payment;
import com.dicore.fatura_yonetim_sistemi.entity.User;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO paymentRequestDTO, Bill bill, User user) {
        Payment payment = new Payment();
        payment.setAmountPaid(paymentRequestDTO.getAmountPaid());
        payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());
        payment.setBill(bill);
        payment.setUser(user);
        return payment;
    }

    public static PaymentResponseDTO toResponseDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setAmountPaid(payment.getAmountPaid());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setBillId(payment.getBill().getId());
        dto.setUserId(payment.getUser().getId());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }
}
