package com.sut.se61.g17.entity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
public class Disease {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "diseaseID", unique = true, nullable = false)
    private @NotNull Long diseaseID;
    private @NotNull String  diseaseName;

    public Disease(){}
    public Disease(String diseaseName){this.diseaseName = diseaseName;}

}
