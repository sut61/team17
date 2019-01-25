package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/carservice")
public class CarserviceController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CarServiceRepository carServiceRepository;
    @Autowired
    private SubDistrictRepository subDistrictRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CarServiceTypeRepository carServiceTypeRepository;

    public CarserviceController(CarServiceRepository carServiceRepository) {this.carServiceRepository = carServiceRepository;}

    @GetMapping(path = "/")
    public Collection<CarService> Carservice() {
        return carServiceRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/car-service-type")
    public Collection<CarServiceType> getAllType(){
        return carServiceTypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/{carservicename}")
    public CarService getCarserviceByName(@PathVariable String carservicename) {return this.carServiceRepository.findByCarServiceName(carservicename);}

    @PostMapping(path = "/{subdistrictID}/{districtID}/{proviceID}/{carservicetypeID}")
    public CarService newCarservice(@RequestBody CarService newCarservice,
                                    @PathVariable Long subdistrictID,
                                    @PathVariable Long districtID,
                                    @PathVariable Long proviceID,
                                    @PathVariable Long carservicetypeID,
                                    CarServiceType carServiceType,
                                    Address address){

        address.setAddress(newCarservice.getAddress().getAddress());
        address.setProvince(provinceRepository.findById(proviceID).get());
        address.setDistrict(districtRepository.findById(districtID).get());
        address.setSubDistrict(subDistrictRepository.findById(subdistrictID).get());
        addressRepository.save(address);
        carServiceType = carServiceTypeRepository.findById(carservicetypeID).get();
        newCarservice.setAddress(address);
        newCarservice.setCarServiceType(carServiceType);
        return carServiceRepository.save(newCarservice);
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
