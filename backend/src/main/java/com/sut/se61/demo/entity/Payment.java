package com.sut.se61.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PAYMENT_ID")
    private Long paymentID;

    @Temporal(TemporalType.DATE)
    private Date payDate;

    @Column(precision = 10, scale = 2)
    private Double amount;


    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    public Payment(Date payDate, Double amount, Invoice invoice, Customer customer, Employee employee) {
        this.payDate = payDate;
        this.amount = amount;
        this.invoice = invoice;
        this.customer = customer;
        this.employee = employee;
    }
}
