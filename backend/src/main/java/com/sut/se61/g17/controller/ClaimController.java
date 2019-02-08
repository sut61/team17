
package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/claim")
public class ClaimController {
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private ClaimTypeRepository claimTypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarServiceRepository carServiceRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    public ClaimController(ClaimRepository claimRepository){this.claimRepository = claimRepository;}

    @GetMapping(path = "/")
    public Collection<Claim> Claim(){
        return claimRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/claimtype")
    public Collection<ClaimType> getClaimType(){
        return claimTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/customer")
    public Collection<Customer> getCustomer(){
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/customer/{personalId}")
    public Customer getCustomer(@PathVariable String personalId){
        return customerRepository.findByIdNumber(personalId);
    }
    @GetMapping(path = "/carservice/{provinceId}")
    public List<CarService> getCarserviceByProvinceID(@PathVariable Long provinceId){
        return carServiceRepository.findByAddress_ProvinceProvinceID(provinceId);
    }
    @GetMapping(path = "/province")
    public Collection<Province> getAllProvince() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping(path = "/{customerID}/{proviceID}/{claimTypeID}/{carServiceID}")
    public Claim newClaim(@RequestBody Claim newClaim,
                          @PathVariable Long customerID,
                          @PathVariable Long proviceID,
                          @PathVariable Long claimTypeID,
                          @PathVariable Long carServiceID,
                          Customer customer,
                          Province province,
                          ClaimType claimType,
                          CarService carService
                          ){

        customer = customerRepository.findById(customerID).get();
        province = provinceRepository.findById(proviceID).get();
        claimType = claimTypeRepository.findById(claimTypeID).get();
        carService = carServiceRepository.findById(carServiceID).get();
        newClaim.setCustomer(customer);
        newClaim.setProvince(province);
        newClaim.setClaimType(claimType);
        newClaim.setCarService(carService);

    return claimRepository.save(newClaim);
    }
}

