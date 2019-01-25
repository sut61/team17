package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Data
@Entity
public class Property {
    @Id
    @SequenceGenerator(name = "property_seq",sequenceName = "property_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "property_seq")
    @Column(name = "PROPERTY_ID")
    private Long propertyID;

    @NotNull
    private String propertyName;

    public Property(@NotNull String propertyName) {
        this.propertyName = propertyName;
    }
}
