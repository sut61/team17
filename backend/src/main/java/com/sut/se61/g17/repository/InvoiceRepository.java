package com.sut.se61.g17.repository;

import com.sut.se61.g17.entity.Invoice;
import com.sut.se61.g17.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findByPolicy_PolicyID(Long id);
    List<Invoice> findByPolicy_PolicyIDAndAndInvoiceStatus_Status(Long id,String status);
    Invoice findByPolicy(Policy policy);
    Boolean existsByPolicy(Policy policy);

}
