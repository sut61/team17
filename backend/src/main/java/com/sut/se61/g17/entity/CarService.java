package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
public class CarService {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "carServiceID", unique = true, nullable = false)
    private @NotNull Long carServiceID;

    @ManyToOne
    @JoinColumn(name = "addressID")
    private Address address;

    @NotNull
    @Column(unique = true)
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-z | A-Z]+")
    private String carServiceName;

    @ManyToOne
    @JoinColumn(name = "carserviceType", nullable = false)
    private CarServiceType carServiceType;
    public CarService(){}

    public CarService(String carServiceName, Address address, CarServiceType carServiceType) {
        this.carServiceName = carServiceName;
        this.address = address;
        this.carServiceType = carServiceType;
    }
    public Long getCarServiceID() {
        return carServiceID;
    }

    public void setCarServiceID(Long carServiceID) {
        this.carServiceID = carServiceID;
    }

    public String getCarServiceName() {
        return carServiceName;
    }

    public void setCarServiceName(String carServiceName) {
        this.carServiceName = carServiceName;
    }

    public CarServiceType getCarServiceType() {
        return carServiceType;
    }

    public void setCarServiceType(CarServiceType carServiceType) {
        this.carServiceType = carServiceType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
