package com.dicore.fatura_yonetim_sistemi.entity;

import com.dicore.fatura_yonetim_sistemi.enums.BillStatus;
import com.dicore.fatura_yonetim_sistemi.enums.BillingUnit;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column
    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingUnit billingUnit;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitUsage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_provider_id", nullable = false)
    private ServiceProvider serviceProvider;

    public Bill() {
    }

    public Bill(BigDecimal amount, LocalDate dueDate, LocalDate paidDate, BillStatus status, BillingUnit billingUnit, BigDecimal unitUsage, User user, ServiceProvider serviceProvider) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.paidDate = paidDate;
        this.status = status;
        this.billingUnit = billingUnit;
        this.unitUsage = unitUsage;
        this.user = user;
        this.serviceProvider = serviceProvider;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public BillingUnit getBillingUnit() {
        return billingUnit;
    }

    public void setBillingUnit(BillingUnit billingUnit) {
        this.billingUnit = billingUnit;
    }

    public BigDecimal getUnitUsage() {
        return unitUsage;
    }

    public void setUnitUsage(BigDecimal unitUsage) {
        this.unitUsage = unitUsage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", paidDate=" + paidDate +
                ", status=" + status +
                ", billingUnit=" + billingUnit +
                ", unitUsage=" + unitUsage +
                ", user=" + user +
                ", serviceProvider=" + serviceProvider +
                '}';
    }
}
