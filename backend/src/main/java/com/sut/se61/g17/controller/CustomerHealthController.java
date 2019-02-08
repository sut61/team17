package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.Customer;
import com.sut.se61.g17.entity.CustomerHealth;
import com.sut.se61.g17.entity.Disease;
import com.sut.se61.g17.repository.CustomerHealthRepository;
import com.sut.se61.g17.repository.CustomerRepository;
import com.sut.se61.g17.repository.DiseaseRepository;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/health" )
public class CustomerHealthController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private CustomerHealthRepository customerHealthRepository;

    @GetMapping(path = "/customer/{personalId}")
    public Customer getCustomerByPersonalId(@PathVariable String personalId){
        return customerRepository.findByIdNumber(personalId);

    }
    @GetMapping(path = "/disease")
    private List<Disease> getDiseases(){
        return diseaseRepository.findAll();
    }

    @PostMapping(path = "/post/{diseaseID}/{idNumber}")
    private CustomerHealth postCustomerHealth(@RequestBody CustomerHealth customerHealth,
                                              @PathVariable Long diseaseID,
                                              @PathVariable String idNumber){
        customerHealth.setCustomer(customerRepository.findByIdNumber(idNumber));
        customerHealth.setDisease(diseaseRepository.findById(diseaseID).get());
        return customerHealthRepository.save(customerHealth);
    }



}
