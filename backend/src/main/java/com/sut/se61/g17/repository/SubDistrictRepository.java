package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.District;
import com.sut.se61.g17.entity.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {
    SubDistrict findBySubDistrictName(String subDistrict);
    Collection<SubDistrict> findAllByDistricts(District district);
}
