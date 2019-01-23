package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.CarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CarDataRepository extends JpaRepository<CarData, Long> {
}
