package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class ClaimType {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "claimtypeID", unique = true, nullable = false)
    private @NotNull Long claimTypeID;
    private String claimTypeName;
    public ClaimType(String claimTypeName){this.claimTypeName = claimTypeName;}

    public Long getClaimTypeID() {
        return claimTypeID;
    }

    public void setClaimTypeID(Long claimTypeID) {
        this.claimTypeID = claimTypeID;
    }

    public String getClaimTypeName() {
        return claimTypeName;
    }

    public void setClaimTypeName(String claimTypeName) {
        this.claimTypeName = claimTypeName;
    }
}
