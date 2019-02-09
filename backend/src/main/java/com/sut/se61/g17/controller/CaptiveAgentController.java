package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                                 @PathVariable Long genderID,
                                 @PathVariable Long subdistrictID,
                                 @PathVariable Long districtID,
                                 @PathVariable Long provinceID,
                                 @PathVariable String birthday,
                                 @PathVariable String passwordCheck
    ) throws Exception {
        if(employee.getUsername().length()<2 || employee.getUsername().length()>20)
            throw new Exception("Username length incorrect!(2-20)");
        if(!employee.getUsername().matches("\\D\\w*"))
            throw new Exception("Username incorrect!");
        if(employeeRepository.existsByUsername(employee.getUsername()))
            throw new Exception("Username already exists");

        if(employee.getIdNumber().length()!=13)
            throw new Exception("IdNumber length incorrect!(13)");
        if(!employee.getIdNumber().matches("\\d+"))
            throw new Exception("IdNumber incorrect!");
        if(employeeRepository.existsByIdNumber(employee.getIdNumber()))
            throw new Exception("IdNumber already exists");

        if(!passwordCheck.equals(employee.getPassword()))
            throw new Exception("Password and Confirm incorrect!");

        if(employee.getFirstName().length()<2 || employee.getFirstName().length()>30)
            throw new Exception("FirstName length incorrect!(2-30)");
        if(!employee.getFirstName().matches("\\D+"))
            throw new Exception("FirstName incorrect!");

        if(employee.getLastName().length()<2 || employee.getLastName().length()>30)
            throw new Exception("LastName length incorrect!(2-30)");
        if(!employee.getLastName().matches("\\D+"))
            throw new Exception("LastName incorrect!");

        if(!employee.getEmail().matches("\\w+[@]\\w+[.]com"))
            throw new Exception("Email incorrect!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate maxDate = LocalDate.now().minusYears(18);
        LocalDate dateOfBirth = LocalDate.parse(birthday, formatter);
        if(dateOfBirth.isAfter(maxDate))
            throw new Exception("Please selected birthday before 18 years of current!");

        if(employee.getPhone().length()<10 || employee.getPhone().length()>10)
            throw new Exception("Phone length incorrect!(10)");
        if(!employee.getPhone().matches("[0]\\d*"))
            throw new Exception("Phone incorrect!");

        try {
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
            employee.setBirthday(dateOfBirth);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Error");
        }



        return  employeeRepository.save(employee);


    }
}
