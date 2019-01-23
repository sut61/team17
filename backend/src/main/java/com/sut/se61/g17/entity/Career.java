package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Career {
    @Id
    @SequenceGenerator(name = "career_seq",sequenceName = "career_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "career_seq")
    @Column(name = "CAREER_ID")
    private Long careerID;

    private String careerName;


    public Career(String careerName) {
        this.careerName = careerName;

    }
}
