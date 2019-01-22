package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CUSTOMER_ID")
    private Long customerID;

    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    private String idNumber;

    @NotNull
    private Byte age;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @NotNull
    @Size(min = 5, max = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "addressID", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(name = "genderID", nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "careerID", nullable = false)
    private Career career;

    public Customer() {
    }

    public Customer(@NotNull @Size(min = 3, max = 30) String firstName,
                    @NotNull @Size(min = 3, max = 30) String lastName,
                    @NotNull String idNumber, @NotNull Byte age, Date birthday,
                    @NotNull @Size(min = 5, max = 20) String phone, Address address, Gender gender, Career career) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.age = age;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.career = career;
    }
}
