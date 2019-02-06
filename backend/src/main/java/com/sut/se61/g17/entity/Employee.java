package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeID;
    @Size(min = 2, max = 20)
    @NotNull
    private String username;
    private String password;
    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String lastName;
    @Size(min = 13, max = 13)
    @Pattern(regexp = "\\d+")
    @Column(unique = true)
    private String idNumber;
    private  String email ;
    private LocalDate birthday;

    //    @NotNull
    @Size(min = 5, max = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "addressID" /*, nullable = false*/)
    private Address address;


    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;

    public Employee(String username, String password, @Size(min = 2, max = 30) String firstName, @Size(min = 2, max = 30) String lastName, @Size(min = 13, max = 13) String idNumber, String email, LocalDate birthday,@Size(min = 5, max = 20) String phone, Address address, Gender gender) {
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
