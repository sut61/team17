package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/beneficiary")
public class BeneficiaryController {

    @Autowired
    private SubDistrictRepository subDistrictRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private RelationshipRepository relationshipRepository;
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private AddressRepository addressRepository;


    @GetMapping(path = "/relationships")
    public List<Relationship> getAllRelationship() {
        return relationshipRepository.findAll();
    }

    @GetMapping(path = "/gender")
    public List<Gender> getAllGender() {
        return genderRepository.findAll();
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

    @GetMapping(path = "/bId/{bId}")
    public Beneficiary getBeneficiaryById(@PathVariable Long bId) {
        return beneficiaryRepository.findById(bId).get();
    }

    @GetMapping(path = "/{policyId}")
    public List<Beneficiary> getBeneficiariesByPolicy(@PathVariable Long policyId) {
        return beneficiaryRepository.findByPolicy(policyRepository.findById(policyId).get());
    }

    @DeleteMapping(path = "/bId/{bId}")
    public void deleteBeneficiaryById(@PathVariable Long bId) {
        beneficiaryRepository.deleteById(bId);
    }


    @PostMapping(path = "/post/{policyId}/{genderId}/{relateId}/{provinceId}/{districtId}/{subdistrictId}")
    @Transactional
    /*** เพื่อไม่ให้เพิ่มข้อมูล address เมื่อเพิ่ม beneficiary ไม่ได้ ทำเป็น Transaction ***/
    public Beneficiary postBeneficiary(@RequestBody Beneficiary beneficiary,
                                       @PathVariable Long policyId,
                                       @PathVariable Long genderId,
                                       @PathVariable Long relateId,
                                       @PathVariable Long provinceId,
                                       @PathVariable Long districtId,
                                       @PathVariable Long subdistrictId,
                                       Address address) {


        address.setAddress(beneficiary.getAddress().getAddress());
        address.setProvince(provinceRepository.findById(provinceId).get());
        address.setDistrict(districtRepository.findById(districtId).get());
        address.setSubDistrict(subDistrictRepository.findById(subdistrictId).get());

        addressRepository.save(address);

        beneficiary.setPolicy(policyRepository.findById(policyId).get());
        beneficiary.setAddress(address);
        beneficiary.setGender(genderRepository.findById(genderId).get());
        beneficiary.setRelationship(relationshipRepository.findById(relateId).get());

        return beneficiaryRepository.save(beneficiary);

    }
}
