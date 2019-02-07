package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Entity
public class ClassProperty {
    @Id
    @SequenceGenerator(name = "ckass_seq",sequenceName = "class_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "class_seq")
    @Column(name = "CLASS_ID")
    private @NotNull Long classID;

    @NotNull
    private String className ;

    public ClassProperty(@NotNull String className) {
        this.className = className;
    }
}
