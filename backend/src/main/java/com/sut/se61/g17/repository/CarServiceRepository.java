package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CarServiceRepository extends JpaRepository<CarService, Long> {
    CarService findByCarServiceName(String carServiceName);
    List<CarService> findByAddress_ProvinceProvinceID(Long provinceID);
}
