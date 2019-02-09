package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeID;


    @NotNull
    @Size(min = 2, max = 20)
    @Pattern(regexp = "\\D\\w*")
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "\\D+")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "\\D+")
    private String lastName;

    @NotNull
    @Size(min = 13, max = 13)
    @Pattern(regexp = "\\d+")
    @Column(unique = true)
    private String idNumber;

    @NotNull
    @Email
    private String email ;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull
    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0]\\d*")
    private String phone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "addressID")
    private Address address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;

    public Employee(@NotNull @Size(min = 2, max = 20) @Pattern(regexp = "\\D\\w*") String username, @NotNull String password, @NotNull @Size(min = 2, max = 30) @Pattern(regexp = "\\D+") String firstName, @NotNull @Size(min = 2, max = 30) @Pattern(regexp = "\\D+") String lastName, @NotNull @Size(min = 13, max = 13) @Pattern(regexp = "\\d+") String idNumber, @NotNull String email, @NotNull LocalDate birthday, @NotNull @Size(min = 10, max = 10) @Pattern(regexp = "[0]\\d*") String phone, @NotNull Address address, @NotNull Gender gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
}
