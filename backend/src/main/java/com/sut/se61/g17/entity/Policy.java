package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
public class Policy {
    @Id
    @SequenceGenerator(name = "policy_seq",sequenceName = "policy_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "policy_seq")
    @Column(name = "POLICY_ID")
    private Long policyID;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate periodStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate periodExpiryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime issuedDate;

    @NotNull
    @Size(min = 17,max = 17)
    private String vin;

    @NotNull
    @Size(min = 5,max = 7)
    @Pattern(regexp = "(\\d?[ก-ฮ])?[ก-ฮ]\\d{3}[1-9]")    //ตัวอย่าง กข 1234, ก 0001 และ 9กข 9999 (เลข 4 ตัวท้ายต้อง 0001 - 9999)
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "CARDATA_ID")
    private CarData carData;

    public Policy(LocalDate periodStartDate, LocalDate periodExpiryDate, LocalDateTime issuedDate, @NotNull @Size(min = 17, max = 17) String vin, @NotNull @Size(min = 5, max = 7) @Pattern(regexp = "(\\d?[ก-ฮ])?[ก-ฮ]\\d{3}[1-9]") String licensePlate, Employee employee, Customer customer, Property property, CarData carData) {
        this.periodStartDate = periodStartDate;
        this.periodExpiryDate = periodExpiryDate;
        this.issuedDate = issuedDate;
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.employee = employee;
        this.customer = customer;
        this.property = property;
        this.carData = carData;
    }
}
