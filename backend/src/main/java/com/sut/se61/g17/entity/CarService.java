package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class CarService {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "carServiceID", unique = true, nullable = false)
    private @NotNull Long carServiceID;
    private String carServiceName;

    @ManyToOne
    @JoinColumn(name = "addressID")
    private Address address;


    @ManyToOne
    @JoinColumn(name = "carserviceType", nullable = false)
    private CarServiceType carServiceType;

    public CarService(String carServiceName, Address address, CarServiceType carServiceType) {
        this.carServiceName = carServiceName;
        this.address = address;
        this.carServiceType = carServiceType;
    }


}
