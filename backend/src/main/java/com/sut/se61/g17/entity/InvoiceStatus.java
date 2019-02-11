package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
public class InvoiceStatus {
    @Id
    @SequenceGenerator(name = "invoice_status_seq",sequenceName = "invoice_status_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "invoice_status_seq")
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Z].*")
    @Size(min = 4,max = 8)
    private String status;

    public InvoiceStatus(String status) {
        this.status = status;
    }
}
