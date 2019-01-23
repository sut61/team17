package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.District;
import com.sut.se61.g17.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByDistrictName(String districtName);
    Collection<District> findAllByProvinces(Province province);
}
