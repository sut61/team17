package com.sut.se61.g17.controller;


import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
class CarDataController {
    @Autowired
    private CarDataRepository carDataRepository;
    @Autowired
    private BranchCarRepository branchCarRepository;
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GearTypeRepository gearTypeRepository;


    @GetMapping("/carTypes")
    public Collection<CarType> carType() {
        return carTypeRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @GetMapping("/branchCars")
    public Collection<BranchCar> branchCar() {
        return branchCarRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @GetMapping("/carColors")
    public Collection<CarColor> carColor() {
        return carColorRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @GetMapping("/gearTypes")
    public Collection<GearType> gearType() {
        return gearTypeRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @PostMapping("/carData/{carTypeID}/{branchCarID}/{carColorID}/{gearTypeID}")
    public CarData postCar(@RequestBody CarData carData,
                           @PathVariable Long carTypeID,
                           @PathVariable Long branchCarID,
                           @PathVariable Long carColorID,
                           @PathVariable Long gearTypeID,
                           CarType carType,
                           BranchCar branchCar,
                           GearType gearType,
                           CarColor carColor){
        carType = carTypeRepository.findById(carTypeID).get();
        branchCar = branchCarRepository.findById(branchCarID).get();
        carColor = carColorRepository.findById(carColorID).get();
        gearType = gearTypeRepository.findById(gearTypeID).get();
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setCarType(carType);
        carData.setGearType(gearType);

        return carDataRepository.save(carData);
    }

}
