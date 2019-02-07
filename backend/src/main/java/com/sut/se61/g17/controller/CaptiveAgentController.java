package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/captiveagent")
public class CaptiveAgentController {
    @Autowired
    private EmployeeRepository employeeRepository;

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

    @PostMapping(path = "/{genderID}/{subdistrictID}/{districtID}/{provinceID}")
    public Employee postEmployee(@RequestBody Employee employee, Address address,
                                 @PathVariable Long genderID,
                                 @PathVariable Long subdistrictID,
                                 @PathVariable Long districtID,
                                 @PathVariable Long provinceID
    ) throws Exception {
        try{
            Gender gender = genderRepository.findById(genderID).get();
            SubDistrict subDistrict = subDistrictRepository.findById(subdistrictID).get();
            District district = districtRepository.findById(districtID).get();
            Province province = provinceRepository.findById(provinceID).get();



            address.setAddress(employee.getAddress().getAddress());
            address.setProvince(province);
            address.setDistrict(district);
            address.setSubDistrict(subDistrict);

            addressRepository.save(address);
            employee.setAddress(address);
            employee.setGender(gender);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Error");
        }



        return  employeeRepository.save(employee);


    }
}
