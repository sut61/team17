package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_seq",sequenceName = "customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_seq")
    @Column(name = "CUSTOMER_ID")
    private @NotNull Long customerID;

     @Size(min = 3, max = 30)
    private String firstName;

      @Size(min = 3, max = 30)
    private String lastName;

    @Size(min = 13,max = 13)
      private String idNumber;

     private  String email ;


     private LocalDate birthday;

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


    public Customer(@Size(min = 3, max = 30) String firstName,
                    @Size(min = 3, max = 30) String lastName,
                    @Size(min = 13, max = 13) String idNumber, String email, LocalDate birthday,
                    @NotNull @Size(min = 5, max = 20) String phone,
                    Address address, Gender gender, Career career) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.career = career;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }
}


