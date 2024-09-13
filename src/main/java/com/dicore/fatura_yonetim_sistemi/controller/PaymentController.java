package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.request.PaymentRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.PaymentResponseDTO;
import com.dicore.fatura_yonetim_sistemi.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Ödeme Yönetimi")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Fatura Ödeme", description = "Bir faturanın ödenmesini sağlar.")
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> payBill(@RequestBody PaymentRequestDTO paymentDTO) {
        PaymentResponseDTO paymentResponse = paymentService.payBill(paymentDTO);
        return ResponseEntity.ok(paymentResponse);
    }
}
