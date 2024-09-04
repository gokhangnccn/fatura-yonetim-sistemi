package com.dicore.fatura_yonetim_sistemi.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String filePath;

    /*
    FetchType.LAZY çünkü Invoice oluşturulduğunuda ilişkili olan Bill entitiysi
    otomatik olarak hemen yüklenmemeli. getBill() denildiği vakit veritabanından
    getirilir.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    public Invoice() {
    }

    public Invoice(String filePath, Bill bill) {
        this.filePath = filePath;
        this.bill = bill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "Id=" + id +
                ", filePath='" + filePath + '\'' +
                ", bill=" + bill +
                '}';
    }
}
