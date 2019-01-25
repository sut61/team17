package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/hospitalApi")
public class HospitalController {
    @Autowired
    private HospitalBranchRepository hospitalBranchRepository;
    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private SubDistrictRepository subDistrictRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/hospital/{typeID}/{provinceID}/{districtID}/{subDistrictID}")
    public HospitalBranch postHospital(@PathVariable Long typeID,
                                       @PathVariable Long provinceID,
                                       @PathVariable Long districtID,
                                       @PathVariable Long subDistrictID,
                                       Hospital hospital,
                                       @RequestBody HospitalBranch hospitalBranch,
                                       HospitalType hospitalType,
                                       Address address,
                                       Province province,
                                       District district,
                                       SubDistrict subDistrict) {

        hospitalType = hospitalTypeRepository.findById(typeID).get();
        province = provinceRepository.findById(provinceID).get();
        district = districtRepository.findById(districtID).get();
        subDistrict = subDistrictRepository.findById(subDistrictID).get();
        address.setAddress(hospitalBranch.getAddress().getAddress());
        address.setDistrict(district);
        address.setProvince(province);
        address.setSubDistrict(subDistrict);
        addressRepository.save(address);


        hospital.setHospitalName(hospitalBranch.getHospital().getHospitalName());
        hospital.setHospitalType(hospitalType);
        hospitalRepository.save(hospital);

        hospitalBranch.setAddress(address);
        hospitalBranch.setHospital(hospital);

        return hospitalBranchRepository.save(hospitalBranch);
    }

    @GetMapping(path = "/branch")
    public Collection<HospitalBranch> getAllBranch(){
        return hospitalBranchRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/type")
    public Collection<HospitalType> getAllType() {
        return hospitalTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/province")
    public Collection<Province> getAllProvince() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/district/{province}")
    public Collection<District> getAllDistrict(@PathVariable Long province) {
        Province province1 = provinceRepository.findById(province).get();
        return districtRepository.findAllByProvinces(province1);
    }

    @GetMapping(path = "/subdistrict/{district}")
    public Collection<SubDistrict> getAllSubDistrict(@PathVariable Long district) {
        District district1 = districtRepository.findById(district).get();
        return subDistrictRepository.findAllByDistricts(district1);
    }
}
