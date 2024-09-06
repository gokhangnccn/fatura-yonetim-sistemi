package com.dicore.fatura_yonetim_sistemi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

}
