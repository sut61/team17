package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.District;
import com.sut.se61.g17.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findByProvinceName(String provinceName);
    Province findByDistricts(Set<District> districts);
}
