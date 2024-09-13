package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.request.BillRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.BillResponseDTO;
import com.dicore.fatura_yonetim_sistemi.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@Tag(name = "Fatura Yönetimi")
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @Operation(summary = "Tüm Faturaları Listele.", description = "Kullanıcıya ait tüm faturaları getirir.")
    @GetMapping
    public ResponseEntity<List<BillResponseDTO>> getAllBills() {
        List<BillResponseDTO> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }

    @Operation(summary = "Belirli Bir Faturayı Göster.", description = "ID'si girilen faturayı görüntüler.")
    @GetMapping("/{billId}")
    public ResponseEntity<BillResponseDTO> getBillById(@PathVariable Long billId) {
        BillResponseDTO bill = billService.getBillById(billId);
        return ResponseEntity.ok(bill);
    }

    @Transactional
    @Operation(summary = "Yeni Fatura Oluştur.", description = "Yeni bir fatura oluşturur.")
    @PostMapping
    public ResponseEntity<BillResponseDTO> createBill(@RequestBody BillRequestDTO billRequestDTO) {
        BillResponseDTO createdBill = billService.createBill(billRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBill);
    }

    @Transactional
    @Operation(summary = "Bir Faturayı Sil.", description = "Belirtilen ID'ye sahip faturayı siler.")
    @DeleteMapping("/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.ok("Bill deleted successfully.");
    }
}
