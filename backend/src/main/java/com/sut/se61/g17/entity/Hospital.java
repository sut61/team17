package com.sut.se61.g17.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_seq", sequenceName = "hospital_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    @Column(name = "hospitalID", unique = true, nullable = false)
    private @NotNull Long hospitalID;
    private String hospitalName;

    @ManyToOne
    @JoinColumn(name = "typeID", nullable = false)
    private HospitalType hospitalType;

    public Hospital(String hospitalName, HospitalType hospitalType) {
        this.hospitalName = hospitalName;
        this.hospitalType = hospitalType;
    }

    public Long getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Long hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public HospitalType getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(HospitalType hospitalType) {
        this.hospitalType = hospitalType;
    }
}


