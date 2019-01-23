package com.sut.se61.g17.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class Province {

    @Id
    @SequenceGenerator(name = "province_seq", sequenceName = "province_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_seq")
    @Column(name = "PROVINCE_ID")
    private @NotNull Long provinceID;

    private @NonNull String provinceName;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PROVINCE_DISTRICT", joinColumns = @JoinColumn(name = "PROVINCE_ID", referencedColumnName = "PROVINCE_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISTRICT_ID", referencedColumnName = "DISTRICT_ID"))
    private Set<District>  districts;

    @OneToMany(mappedBy = "province")
    @JsonBackReference
    private Set<Address> address;


    public Province(@NonNull String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Long provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}
