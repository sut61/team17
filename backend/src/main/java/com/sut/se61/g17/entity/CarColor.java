package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data

public class CarColor {
    @Id
    @SequenceGenerator(name="carColor_seq",sequenceName="carColor_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carColor_seq")
    @Column(name="ColorID")
    private @NonNull Long colorID;
    private @NonNull String color;

    public CarColor() {
    }

    public CarColor(String color) {
        this.color = color;
    }


}

