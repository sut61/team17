package com.sut.se61.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date invoiceDate;

    @NotNull
    private Double invoiceAmount;

    @NotNull
    private Double arrears;


    @ManyToOne
    @JoinColumn(name = "ID")
    private InvoiceStatus invoiceStatus;

    @ManyToOne
    @JoinColumn(name = "POLICY_ID")
    private Policy policy;

    public Invoice(@NotNull Date invoiceDate, @NotNull Double invoiceAmount, @NotNull Double arrears, InvoiceStatus invoiceStatus, Policy policy) {
        this.invoiceDate = invoiceDate;
        this.invoiceAmount = invoiceAmount;
        this.arrears = arrears;
        this.invoiceStatus = invoiceStatus;
        this.policy = policy;
    }
}
