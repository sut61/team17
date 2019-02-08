package com.sut.se61.g17.repository;
import com.sut.se61.g17.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public
interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatus(String status);
}

