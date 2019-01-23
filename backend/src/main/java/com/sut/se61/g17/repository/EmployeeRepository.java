package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String s);
}
