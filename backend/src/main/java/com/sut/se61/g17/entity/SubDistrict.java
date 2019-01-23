package com.sut.se61.g17.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class SubDistrict {
    @Id
    @SequenceGenerator(name = "sub_district_seq", sequenceName = "sub_district_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_district_seq")
    @Column(name = "SUB_DISTRICT_ID")
    private @NotNull Long subDistrictID;
    private String subDistrictName;
    @JsonIgnore
    @ManyToMany(mappedBy = "subDistricts", fetch = FetchType.LAZY)
    private Set<District> districts ;

    @OneToMany(mappedBy = "subDistrict")
    @JsonBackReference
    private Set<Address> address;

    public SubDistrict(String subDistrictName) {
        this.subDistrictName = subDistrictName;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public Long getSubDistrictID() {
        return subDistrictID;
    }

    public void setSubDistrictID(Long subDistrictID) {
        this.subDistrictID = subDistrictID;
    }

    public String getSubDistrictName() {
        return subDistrictName;
    }

    public void setSubDistrictName(String subDistrictName) {
        this.subDistrictName = subDistrictName;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}
