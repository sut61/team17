package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
public class HospitalBranch {
    @Id
    @SequenceGenerator(name = "hospital_seq", sequenceName = "hospital_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    @Column(name = "hospitalBranchID", unique = true, nullable = false)
    private @NotNull Long hospitalBranchID;
    private String branchName;
    private String phone;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressID")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "hospitalID")
    private Hospital hospital;


    public HospitalBranch(String branchName, String phone, Address address, Hospital hospital) {
        this.branchName = branchName;
        this.phone = phone;
        this.address = address;
        this.hospital = hospital;
    }

    public Long getHospitalBranchID() {
        return hospitalBranchID;
    }

    public void setHospitalBranchID(Long hospitalBranchID) {
        this.hospitalBranchID = hospitalBranchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }


}
