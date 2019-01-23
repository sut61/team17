package com.sut.se61.g17.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

//@Data // is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class District {
    @Id
    @SequenceGenerator(name = "district_seq", sequenceName = "district_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "district_seq")
    @Column(name = "DISTRICT_ID")
    private @NotNull Long districtID;
    private String districtName;
    @JsonIgnore
    @ManyToMany(mappedBy = "districts")
    private Set<Province> provinces;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "DISTRICT_SUBDISTRICT", joinColumns = @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "DISTRICT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUB_DISTRICT_ID", referencedColumnName = "SUB_DISTRICT_ID"))
    private Set<SubDistrict> subDistricts ;

    @OneToMany(mappedBy = "district")
    @JsonBackReference
    private Set<Address> address;



    public District(String districtName) {
        this.districtName = districtName;
    }

    public Long getDistrictID() {
        return districtID;
    }

    public void setDistrictID(Long districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Set<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }

    public Set<SubDistrict> getSubDistricts() {
        return subDistricts;
    }

    public void setSubDistricts(Set<SubDistrict> subDistricts) {
        this.subDistricts = subDistricts;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}
