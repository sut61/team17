package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CaptiveAgentController(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @PostMapping(path = "/{genderID}/{subdistrictID}/{districtID}/{provinceID}/{birthday}/{passwordCheck}")
    public Employee postEmployee(@RequestBody Employee employee, Address address,
                                 @PathVariable String genderID,             //Received with string to prevent 'undefined' not convert to Long
                                 @PathVariable String subdistrictID,        //Received with string to prevent 'undefined' not convert to Long
                                 @PathVariable String districtID,           //Received with string to prevent 'undefined' not convert to Long
                                 @PathVariable String provinceID,           //Received with string to prevent 'undefined' not convert to Long
                                 @PathVariable String birthday,             //Received with string to format LocalDate
                                 @PathVariable String passwordCheck
    ) throws Exception {
        //=====This part not use annotation=====
        //prevent Password and Confirm not same value
        if(!passwordCheck.equals(employee.getPassword()))
            throw new Exception("Password and Confirm incorrect!");

        //prevent null value
        if(genderID.equals("undefined") || genderID.equals("null"))
            throw new Exception("Please select gender before save!");

        //prevent null value
        if(subdistrictID.equals("undefined") || subdistrictID.equals("null") ||
                districtID.equals("undefined") || districtID.equals("null") ||
                provinceID.equals("undefined") || provinceID.equals("null") ||
                employee.getAddress().getAddress()==null || employee.getAddress().getAddress().isEmpty())
            throw new Exception("Please enter address before save!");

        //convert string to long for set value
        Long genderIDLong = Long.valueOf(genderID);
        Long subdistrictIDLong = Long.valueOf(subdistrictID);
        Long districtIDLong = Long.valueOf(districtID);
        Long provinceIDLong = Long.valueOf(provinceID);

        //pattern string to localdate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //set maxdate to prevent age less than 18 years old
        LocalDate maxDate = LocalDate.now().minusYears(18);
        //convert string to localdate
        LocalDate dateOfBirth = LocalDate.parse(birthday, formatter);
        //prevent the date after 18 years before the current
        if(dateOfBirth.isAfter(maxDate))
            throw new Exception("Please selected birthday before 18 years of current!");
        //=====This part not use annotation=====

        try {
            Gender gender = genderRepository.findById(genderIDLong).get();
            SubDistrict subDistrict = subDistrictRepository.findById(subdistrictIDLong).get();
            District district = districtRepository.findById(districtIDLong).get();
            Province province = provinceRepository.findById(provinceIDLong).get();

            address.setAddress(employee.getAddress().getAddress());
            address.setProvince(province);
            address.setDistrict(district);
            address.setSubDistrict(subDistrict);
            addressRepository.save(address);

            employee.setAddress(address);
            employee.setGender(gender);
            employee.setBirthday(dateOfBirth);
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword())); //PasswordEncoder

            return  employeeRepository.saveAndFlush(employee);
        }catch (ConstraintViolationException e) {
            String message = e.getConstraintViolations().iterator().next().getMessage();
            throw new Exception(message);
        }catch (DataIntegrityViolationException e){                 //detect @Column(unique = true)
            String message = e.getMostSpecificCause().getMessage();
            System.out.println("=====================================================\n\n");
            System.out.println(message);
            System.out.println("\n\n=====================================================");
            if(message.matches("^Unique.*EMPLOYEE\\(USERNAME\\).*\\n.*"))
                throw new Exception("Username already exists");
            if(message.matches("^Unique.*EMPLOYEE\\(ID_NUMBER\\).*\\n.*"))
                throw new Exception("IdNumber already exists");
            throw new Exception(message);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Error");
        }
    }
}
