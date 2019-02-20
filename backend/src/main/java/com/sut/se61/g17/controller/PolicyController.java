package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
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
    private PropertyPolicyRepository propertyPolicyRepository;
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
    public Collection<PropertyPolicy> getProperty(){
        return propertyPolicyRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/property/{propertyID}")
    public PropertyPolicy getOneProperty(@PathVariable Long propertyID){
        return propertyPolicyRepository.findById(propertyID).get();
    }


    @GetMapping(path = "/customer")
    public Collection<Customer> getCustomer(){
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping(path = "/customer/{idNumber}")
    public Customer getOneCustomer(@PathVariable String idNumber){
            return customerRepository.findByIdNumber(idNumber);
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
                             @PathVariable String propertyID,       //Received with string to prevent 'undefined' not convert to Long
                             @PathVariable String customerID,       //Received with string to prevent 'undefined' not convert to Long
                             @PathVariable String carID,            //Received with string to prevent 'undefined' not convert to Long
                             @PathVariable String username,
                             @PathVariable String periodStartDate,
                             @PathVariable String periodYear        //Received with string to prevent 'undefined' not convert to Byte
    ) throws Exception {
        //=====This part not use annotation=====
        //prevent null value
        if(propertyID.equals("undefined") || propertyID.equals("null"))
            throw new Exception("Please select property before save!");

        //prevent null value
        if(customerID.equals("undefined") || customerID.equals("null"))
            throw new Exception("Please click search before save!");

        //prevent null value
        if(carID.equals("undefined") || carID.equals("null"))
            throw new Exception("Please enter car data before save!");

        //prevent null value
        if(periodYear.equals("undefined") || periodYear.equals("null"))
            throw new Exception("Please select period before save!");
        //convert string to long,byte for set value
        Long propertyIDLong = Long.valueOf(propertyID);
        Long customerIDLong = Long.valueOf(customerID);
        Long carIDLong = Long.valueOf(carID);
        Byte periodYearLong = Byte.valueOf(periodYear);             //value between 1-10
        //prevent periodExpiryDate more than 10 years
        if(periodYearLong<1 || periodYearLong > 10)
            throw new Exception("Period incorrect!");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate maxDate = LocalDate.now().plusMonths(1);
        LocalDate dateStart = LocalDate.parse(periodStartDate, formatter);
        //prevent the date after 1 month of current
        if(dateStart.isAfter(maxDate))
            throw new Exception("Please selected date not more than 1 month of current!");
        //prevent the date before of current
        if(dateStart.isBefore(LocalDate.now()))
            throw new Exception("Please selected date equal or more than of current!");
        //=====This part not use annotation=====
        try{
            PropertyPolicy property = propertyPolicyRepository.findById(propertyIDLong).get();
            Customer customer = customerRepository.findById(customerIDLong).get();
            CarData carData = carDataRepository.findById(carIDLong).get();
            Employee employee = employeeRepository.findByUsername(username);
            LocalDate dateExpiry = dateStart.plusYears(periodYearLong);
            LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));

            policy.setIssuedDate(dateTimeNow);
            policy.setPropertyPolicy(property);
            policy.setCustomer(customer);
            policy.setCarData(carData);
            policy.setEmployee(employee);
            policy.setPeriodStartDate(dateStart);
            policy.setPeriodExpiryDate(dateExpiry);

            return policyRepository.saveAndFlush(policy);
        }catch (ConstraintViolationException e) {
            String message = e.getConstraintViolations().iterator().next().getMessage();
            String value = e.getConstraintViolations().iterator().next().getPropertyPath().toString();
            throw new Exception(value+" "+message);
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Error ");
        }
    }
}
