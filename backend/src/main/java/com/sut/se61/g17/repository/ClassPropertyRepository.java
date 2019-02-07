package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.ClassProperty;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassPropertyRepository extends JpaRepository<ClassProperty,Long> {
    ClassProperty findByClassName(String s);
}
