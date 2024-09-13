package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.dtos.request.PaymentRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.PaymentResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.Payment;
import com.dicore.fatura_yonetim_sistemi.enums.BillStatus;
import com.dicore.fatura_yonetim_sistemi.exception.EntityNotFoundException;
import com.dicore.fatura_yonetim_sistemi.exception.InvalidPaymentException;
import com.dicore.fatura_yonetim_sistemi.mapper.PaymentMapper;
import com.dicore.fatura_yonetim_sistemi.repository.BillRepository;
import com.dicore.fatura_yonetim_sistemi.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BillRepository billRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, BillRepository billRepository) {
        this.paymentRepository = paymentRepository;
        this.billRepository = billRepository;
    }

    @Transactional
    public PaymentResponseDTO payBill(PaymentRequestDTO paymentRequestDTO) {
        Bill bill = billRepository.findById(paymentRequestDTO.getBillId())
                .orElseThrow(() -> new EntityNotFoundException("Bill", paymentRequestDTO.getBillId()));

        if (bill.getStatus() == BillStatus.PAID) {
            throw new InvalidPaymentException("Fatura zaten tamamen ödenmiş.");
        }

        BigDecimal remainingAmount = bill.getAmount();

        if (paymentRequestDTO.getAmountPaid().compareTo(remainingAmount) > 0) {
            throw new InvalidPaymentException("Ödeme miktarı fatura tutarını aşamaz.");
        }

        Payment payment = PaymentMapper.toEntity(paymentRequestDTO, bill, bill.getUser());
        payment.setPaymentDate(LocalDateTime.now());
        Payment savedPayment = paymentRepository.save(payment);

        BigDecimal newRemainingAmount = remainingAmount.subtract(paymentRequestDTO.getAmountPaid());
        bill.setAmount(newRemainingAmount);

        if (newRemainingAmount.compareTo(BigDecimal.ZERO) == 0) {
            bill.setStatus(BillStatus.PAID);
            bill.setPaidDate(LocalDate.now());
        } else {
            bill.setStatus(BillStatus.PARTIAL_PAID);
        }

        billRepository.save(bill);

        return PaymentMapper.toResponseDTO(savedPayment);
    }
}
