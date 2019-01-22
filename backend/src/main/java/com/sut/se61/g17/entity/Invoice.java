package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Invoice {
    @Id
    @SequenceGenerator(name = "invoice_seq",sequenceName = "invoice_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_seq")
    @Column(name = "INVOICE_ID")
    private Long invoiceID;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;

    @NotNull
    private Double invoiceAmount;


    @ManyToOne
    @JoinColumn(name = "INVOICE_STATUS_ID")
    private InvoiceStatus invoiceStatus;

    @ManyToOne
    @JoinColumn(name = "POLICY_ID")
    private Policy policy;

    public Invoice(@NotNull LocalDate invoiceDate, @NotNull Double invoiceAmount, InvoiceStatus invoiceStatus, Policy policy) {
        this.invoiceDate = invoiceDate;
        this.invoiceAmount = invoiceAmount;
        this.invoiceStatus = invoiceStatus;
        this.policy = policy;
    }

    public Long getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Long invoiceID) {
        this.invoiceID = invoiceID;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }
}
