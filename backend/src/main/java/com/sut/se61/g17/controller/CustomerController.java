package com.sut.se61.g17.controller;


import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CareerRepository careerRepository;

     @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private SubDistrictRepository subDistrictRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping(path = "/")
    public Collection<Customer> getCustomer(){
      return customerRepository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping(path = "/{customerID}")
    public Customer getOneCustomer(@PathVariable Long customerID){
        return customerRepository.findById(customerID).get();
    }


   @PostMapping(path = "/{careerID}/{genderID}/{subdistrictID}/{districtID}/{proviceID}")
    public Customer postCustomer(@RequestBody Customer customer, Address address,
                                 @PathVariable Long careerID,
                                 @PathVariable Long genderID,
                                 @PathVariable Long subdistrictID,
                                 @PathVariable Long districtID,
                                 @PathVariable Long proviceID
                                 ) throws Exception {
        try{
            Career careerName1 = careerRepository.findById(careerID).get();
            Gender gender1 = genderRepository.findById(genderID).get();
            SubDistrict subDistrict = subDistrictRepository.findById(subdistrictID).get();
            District district = districtRepository.findById(districtID).get();
            Province province = provinceRepository.findById(proviceID).get();



            address.setAddress(customer.getAddress().getAddress());
            address.setProvince(province);
            address.setDistrict(district);
            address.setSubDistrict(subDistrict);

            addressRepository.save(address);
            customer.setAddress(address);
            customer.setCareer(careerName1);
            customer.setGender(gender1);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Error");
        }



       return  customerRepository.save(customer);


   }


 @GetMapping(path = "/career")
 public Collection<Career> getCareer(){return careerRepository.findAll().stream().collect(Collectors.toList());}

    @GetMapping(path = "/career/{careerID}")
    public Career getOneCareer(@PathVariable Long careerID){
        return careerRepository.findById(careerID).get();
    }
    @GetMapping(path = "/gender")
    public Collection<Gender> getGender(){return genderRepository.findAll().stream().collect(Collectors.toList());}

    @GetMapping(path = "/gender/{gerderID}")
    public Gender getOneGender(@PathVariable Long genderID){
        return genderRepository.findById(genderID).get();
    }


    @GetMapping(path = "/province")
    public Collection<Province> getAllProvince() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/district/{provinceID}")
    public Collection<District> getAllDistrict(@PathVariable Long provinceID) {
        Province province1 = provinceRepository.findById(provinceID).get();
        return districtRepository.findAllByProvinces(province1);
    }
    @GetMapping(path = "/subdistrict/{districtID}")
    public Collection<SubDistrict> getAllSubDistrict(@PathVariable Long districtID) {
        District district1 = districtRepository.findById(districtID).get();
        return subDistrictRepository.findAllByDistricts(district1);
    }


}

