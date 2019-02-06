package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.Beneficiary;
import com.sut.se61.g17.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BeneficiaryRepository extends JpaRepository<Beneficiary,Long> {
    List<Beneficiary> findByPolicy(Policy policy);
}
