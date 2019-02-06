package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Relationship {

    @Id
    @SequenceGenerator(name = "relationship_seq",sequenceName = "relationship_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "relationship_seq")
    @Column(name = "relationship_id")
    private Long relationshipId;

    @NotNull
    private String relationship;

    public Relationship(@NotNull String relationship) {
        this.relationship = relationship;
    }
}
