package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.CarData;
import com.sut.se61.g17.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public
interface CarTypeRepository extends JpaRepository<CarType, Long> {
    CarType findByCarType(String s);
//    Collection<CarType> findAllByCarData(CarData carData);
//    Collection<CarType> findAllByCarDatas(CarData carData);
//    Collection<CarType> findAllByCarDataes(CarData carData);
}

