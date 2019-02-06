package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/policy")
public class PolicyController {
    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CarDataRepository carDataRepository;
    @Autowired
    private BranchCarRepository branchCarRepository;
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private GearTypeRepository gearTypeRepository;

    @GetMapping(path = "/property")
    public Collection<Property> getProperty(){
        return propertyRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/property/{propertyID}")
    public Property getOneProperty(@PathVariable Long propertyID){
        return propertyRepository.findById(propertyID).get();
    }


    @GetMapping(path = "/customer")
    public Collection<Customer> getCustomer(){
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/customer/{idNumber}")
    public Customer getOneCustomer(@PathVariable String idNumber)
    throws Exception{
        try {
            return customerRepository.findByIdNumber(idNumber);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("ID Number Incorrect");
        }
    }


    @GetMapping(path = "/carData")
    public Collection<CarData> getCarData(){
        return carDataRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/carData/{carID}")
    public CarData getOneCarData(@PathVariable Long carID){
        return carDataRepository.findById(carID).get();
    }
    
    @GetMapping("/branchCars")
    public Collection<BranchCar> branchCar() {
        return branchCarRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/carColors")
    public Collection<CarColor> carColor() {
        return carColorRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/gearTypes")
    public Collection<GearType> gearType() {
        return gearTypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/carTypes")
    private Collection<CarType> getCarTypes(){
        return carTypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/carDatas/{branchId}/{colorID}/{carTypeID}/{gearTypeID}")
    private Collection<CarData> getCarDatasTypes(@PathVariable Long branchId,
                                                 @PathVariable Long colorID,
                                                 @PathVariable Long carTypeID,
                                                 @PathVariable Long gearTypeID){
        return carDataRepository.findByBranchCar_BranchIDAndCarColor_ColorIDAndCarType_CarTypeIDAndGearType_GearTypeID(branchId,colorID,carTypeID,gearTypeID);
    }

    @PostMapping(path = "/{propertyID}/{customerID}/{carID}/{username}/{periodStartDate}/{periodYear}")
    public Policy postPolicy(@RequestBody Policy policy,
                             @PathVariable Long propertyID,
                             @PathVariable Long customerID,
                             @PathVariable Long carID,
                             @PathVariable String username,
                             @PathVariable String periodStartDate,
                             @PathVariable Byte periodYear
    ) throws Exception {

        if(periodYear<1 || periodYear > 10)
            throw new Exception("Period incorrect!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate maxDate = LocalDate.now().plusMonths(1);
        LocalDate dateStart = LocalDate.parse(periodStartDate, formatter);
        if(dateStart.isAfter(maxDate))
            throw new Exception("Please selected date not more than 1 month of current!");
        if(dateStart.isBefore(LocalDate.now()))
            throw new Exception("Please selected date equal or more than of current!");
        if(!policy.getLicensePlate().matches("(\\d?[ก-ฮ])?[ก-ฮ]\\d{3}[1-9]"))
            throw new Exception("LicensePlate Incorrect!");
        if(policy.getVin().length()!=17)
            throw new Exception("VIN Incorrect!");
        try{
            Property property = propertyRepository.findById(propertyID).get();
            Customer customer = customerRepository.findById(customerID).get();
            CarData carData = carDataRepository.findById(carID).get();
            Employee employee = employeeRepository.findByUsername(username);
            LocalDate dateExpiry = dateStart.plusYears(periodYear);
            LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));

            policy.setIssuedDate(dateTimeNow);
            policy.setProperty(property);
            policy.setCustomer(customer);
            policy.setCarData(carData);
            policy.setEmployee(employee);
            policy.setPeriodStartDate(dateStart);
            policy.setPeriodExpiryDate(dateExpiry);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Error ");
        }
        return policyRepository.save(policy);
    }
}
