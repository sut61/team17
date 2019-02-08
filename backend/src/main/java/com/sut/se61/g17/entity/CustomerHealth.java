package com.sut.se61.g17.entity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class CustomerHealth {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "healthID", unique = true, nullable = false)
    private @NotNull Long healthID;


    @Min(value = 18)
    @Max(value = 100)
    private int age;

    @Min(value = 100)
    @Max(value = 250)
    private int height;

    @Min(value = 30)
    @Max(value = 200)
    private int weight;

    @NotNull
    @Size(min = 2 ,max = 30)
    @Pattern(regexp = "^([ก-๙]|[0-9]|[ ./*\\-])+")
    private String vivisection;

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^([ก-๙]|[0-9]|[ ./*\\-])+")
    private String medicine;

    @ManyToOne
    @JoinColumn(name = "diseaseID")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID",unique = true)
    private Customer customer;

    public CustomerHealth(){}

    public Long getHealthID() {
        return healthID;
    }

    public void setHealthID(Long healthID) {
        this.healthID = healthID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getVivisection() {
        return vivisection;
    }

    public void setVivisection(String vivisection) {
        this.vivisection = vivisection;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
