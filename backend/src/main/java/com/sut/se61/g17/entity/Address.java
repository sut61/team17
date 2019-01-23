package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class Address {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "addressID", unique = true, nullable = false)
    private @NotNull Long addressID;
    private String address;

    @ManyToOne
    @JoinColumn(name = "DistrictID", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID", nullable = false)
    private Province province;


    @ManyToOne
    @JoinColumn(name = "SubDistrictID", nullable = false)
    private SubDistrict subDistrict;

    public Address(String address, District district, Province province, SubDistrict subDistrict) {
        this.address = address;
        this.district = district;
        this.province = province;
        this.subDistrict = subDistrict;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
    }
}
