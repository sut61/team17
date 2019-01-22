package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus,Long> {
    InvoiceStatus findByStatus(String s);
}
