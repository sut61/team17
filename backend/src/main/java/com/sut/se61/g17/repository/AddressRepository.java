package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.Address;
import com.sut.se61.g17.entity.District;
import com.sut.se61.g17.entity.Province;
import com.sut.se61.g17.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByAddressAndProvinceAndDistrictAndSubDistrict(String address, Province province, District district, SubDistrict subDistrict);
}
