package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.request.InvoiceRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.InvoiceResponseDTO;
import com.dicore.fatura_yonetim_sistemi.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Fiş Yönetimi")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@Valid @RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceResponseDTO createdInvoice = invoiceService.createInvoice(invoiceRequestDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceResponseDTO invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/bill/{billId}")
    public ResponseEntity<List<InvoiceResponseDTO>> getInvoicesByBillId(@PathVariable Long billId) {
        List<InvoiceResponseDTO> invoices = invoiceService.getInvoicesByBillId(billId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/bill/{billId}/latest")
    public ResponseEntity<InvoiceResponseDTO> getLatestInvoiceByBillId(@PathVariable Long billId) {
        InvoiceResponseDTO latestInvoice = invoiceService.getLatestInvoiceByBillId(billId);
        return ResponseEntity.ok(latestInvoice);
    }

}
