package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data

public class BranchCar {
    @Id
    @SequenceGenerator(name="branchCar_seq",sequenceName="branchCar_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="branchCar_seq")
    @Column(name="BranchID")
    private @NonNull Long branchID;
    private @NonNull String branchName;

    public BranchCar() {
    }

    public BranchCar(String branchName) {
        this.branchName = branchName;
    }


}
