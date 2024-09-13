package com.dicore.fatura_yonetim_sistemi.repository;

import com.dicore.fatura_yonetim_sistemi.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByBillId(Long billId);
    Optional<Invoice> findFirstByBillIdOrderByIdDesc(Long billId);

}
