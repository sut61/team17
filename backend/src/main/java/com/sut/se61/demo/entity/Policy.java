package com.sut.se61.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long policyID;

    public Policy() {
    }

    public Long getPolicyID() {
        return policyID;
    }

    public void setPolicyID(Long policyID) {
        this.policyID = policyID;
    }
}
