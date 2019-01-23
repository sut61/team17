package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data

public class CarType {
    @Id
    @SequenceGenerator(name="carType_seq",sequenceName="carType_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carType_seq")
    @Column(name="TypeID",unique = true, nullable = false)


    private @NonNull Long carTypeID;
    private @NonNull String carType;

    public CarType() {
    }

    public CarType(String carType) {
        this.carType = carType;
    }


}
