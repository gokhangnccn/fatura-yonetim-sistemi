package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.exception.EntityNotFoundException;
import com.dicore.fatura_yonetim_sistemi.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicore.fatura_yonetim_sistemi.dtos.request.InvoiceRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.InvoiceResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.Invoice;
import com.dicore.fatura_yonetim_sistemi.mapper.InvoiceMapper;
import com.dicore.fatura_yonetim_sistemi.repository.BillRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final BillRepository billRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, BillRepository billRepository) {
        this.invoiceRepository = invoiceRepository;
        this.billRepository = billRepository;
    }

    @Transactional
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        Bill bill = billRepository.findById(invoiceRequestDTO.getBillId())
                .orElseThrow(() -> new EntityNotFoundException("Bill", invoiceRequestDTO.getBillId()));

        Invoice invoice = InvoiceMapper.toEntity(invoiceRequestDTO, bill);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return InvoiceMapper.toResponseDTO(savedInvoice);
    }

    public InvoiceResponseDTO getInvoiceById(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new EntityNotFoundException("Invoice", invoiceId));
        return InvoiceMapper.toResponseDTO(invoice);
    }

    public List<InvoiceResponseDTO> getInvoicesByBillId(Long billId) {
        List<Invoice> invoices = invoiceRepository.findByBillId(billId);
        return invoices.stream()
                .map(InvoiceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public InvoiceResponseDTO getLatestInvoiceByBillId(Long billId) {
        Invoice invoice = invoiceRepository.findFirstByBillIdOrderByIdDesc(billId)
                .orElseThrow(() -> new EntityNotFoundException("Invoice", "for Bill ID " + billId));
        return InvoiceMapper.toResponseDTO(invoice);
    }
}


