package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class CarServiceType {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "serviceTypeID", unique = true, nullable = false)
    private @NotNull Long serviceTypeID;
    private String carServiceType;

    public CarServiceType(String carServiceType) {
        this.carServiceType = carServiceType;
    }
}
