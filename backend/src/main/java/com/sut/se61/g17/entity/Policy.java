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
    @Pattern(regexp = "[A-Za-z0-9]{17}")
    private String vin;

    @NotNull
    @Pattern(regexp = "(\\d?[ก-ฮ])?[ก-ฮ]((0{3}[1-9])|" +              //pattern มี ศูนย์ 3 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 1-9
            "(0{2}[1-9]\\d)|([1-9]0{2}\\d)|(0[1-9]0\\d)|" +         //pattern มี ศูนย์ 2 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 0-9
            "(0[1-9]{2}\\d)|([1-9]{2}0\\d)|([1-9]0[1-9]\\d)|" +     //pattern มี ศูนย์ 1 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 0-9
            "([1-9]{3}\\d))",message = "must match example \"กข1234, ก0001 และ 9กข9999 (เลข 4 ตัวท้ายต้อง 0001 - 9999)\"")                //pattern มี ศูนย์ 0 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 0-9
    private String licensePlate;                                    // ตัวอย่าง กข1234, ก0001 และ 9กข9999 (เลข 4 ตัวท้ายต้อง 0001 - 9999)

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private PropertyPolicy propertyPolicy;

    @ManyToOne
    @JoinColumn(name = "CARDATA_ID")
    private CarData carData;

    public Policy(LocalDate periodStartDate, LocalDate periodExpiryDate, LocalDateTime issuedDate, @NotNull @Pattern(regexp = "[A-Za-z0-9]{17}") String vin, @NotNull @Size(min = 5, max = 7) @Pattern(regexp = "(\\d?[ก-ฮ])?[ก-ฮ]((0{3}[1-9])|" +              //pattern มี ศูนย์ 3 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 1-9
            "(0{2}[1-9]\\d)|([1-9]0{2}\\d)|(0[1-9]0\\d)|" +         //pattern มี ศูนย์ 2 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 0-9
            "(0[1-9]{2}\\d)|([1-9]{2}0\\d)|([1-9]0[1-9]\\d)|" +     //pattern มี ศูนย์ 1 ตัว ใน3ตัวแรกของตัวเลข4หลัก จะต้องลงท้าย 0-9
            "([1-9]{3}\\d))") String licensePlate, Employee employee, Customer customer, PropertyPolicy propertyPolicy, CarData carData) {
        this.periodStartDate = periodStartDate;
        this.periodExpiryDate = periodExpiryDate;
        this.issuedDate = issuedDate;
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.employee = employee;
        this.customer = customer;
        this.propertyPolicy = propertyPolicy;
        this.carData = carData;
    }
}
