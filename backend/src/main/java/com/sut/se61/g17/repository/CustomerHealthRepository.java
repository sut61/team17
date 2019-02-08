package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.CustomerHealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CustomerHealthRepository extends JpaRepository <CustomerHealth, Long>{
}
