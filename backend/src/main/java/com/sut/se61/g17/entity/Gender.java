package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Gender {
    @Id
    @SequenceGenerator(name = "gender_seq",sequenceName = "gender_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gender_seq")
    @Column(name = "GENDER_ID")
    private Long genderID;

    private String genderType;



    public Gender(String genderType) {
        this.genderType = genderType;
    }
}
