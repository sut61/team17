package com.sut.se61.g17.sprint1;

import com.sut.se61.g17.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PolicyTests {
    @Autowired
    private TestEntityManager entityManager;

    Employee employee = new Employee();
    Address address = new Address();
    Province province = new Province();
    District district = new District();
    SubDistrict subDistrict = new SubDistrict();
    Gender gender = new Gender();
    CarData carData = new CarData();
    BranchCar branchCar = new BranchCar();
    CarType carType = new CarType();
    CarColor carColor = new CarColor();
    GearType gearType = new GearType();
    Customer customer = new Customer();
    Career career = new Career();
    Policy policy = new Policy();
    ClassProperty classProperty = new ClassProperty();
    PropertyPolicy propertyPolicy = new PropertyPolicy();
    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        try {

            /*** ------------------------ เพิ่มข้อมูลเพศก่อนจะทดสอบ -------------------------***/

            gender.setGenderType("male");
            entityManager.persist(gender);
            entityManager.flush();

            /***-----------------------------------------------------------------***/

            /***------------------------ ข้อมูลที่อยู่ก่อนจะทดสอบ ------------------------***/
            province.setProvinceName("สมุทรปราการ");
            entityManager.persistAndFlush(province);
            district.setDistrictName("พระประแดง");
            entityManager.persistAndFlush(district);
            subDistrict.setSubDistrictName("บางหัวเสือ");
            entityManager.persistAndFlush(subDistrict);

            address.setAddress("7/50 m.6");
            address.setProvince(entityManager.persistFlushFind(province));
            address.setDistrict(district);
            address.setSubDistrict(subDistrict);
            entityManager.persistAndFlush(address);
            /***-----------------------------------------------------------------***/

            /***------------------------ ข้อมูลพนักงานอยู่ก่อนจะทดสอบ ------------------------***/
            employee.setUsername("admin");
            employee.setPassword("admin");
            employee.setFirstName("Sivaroot");
            employee.setLastName("Chuncharoen");
            employee.setGender(gender);
            employee.setAddress(address);
            employee.setIdNumber("1111111111115");
            employee.setBirthday(LocalDate.now().minusYears(18));
            employee.setPhone("0991230880");
            employee.setEmail("sivaroot@gmail.com");
            entityManager.persistAndFlush(employee);
            /***-----------------------------------------------------------------***/

            /***------------------------ ข้อมูลรถอยู่ก่อนจะทดสอบ ------------------------***/
            carType.setCarType("4-door");
            entityManager.persistAndFlush(carType);
            gearType.setGearType("AUTO");
            entityManager.persistAndFlush(gearType);
            branchCar.setBranchName("HONDA");
            entityManager.persistAndFlush(branchCar);
            carColor.setColor("Titanium");
            entityManager.persistAndFlush(carColor);
            carData.setcC("500");
            carData.setModel("CITY 2020");
            carData.setBranchCar(entityManager.persistFlushFind(branchCar));
            carData.setCarColor(entityManager.persistFlushFind(carColor));
            carData.setCarType(entityManager.persistFlushFind(carType));
            carData.setGearType(entityManager.persistFlushFind(gearType));
            entityManager.persistAndFlush(carData);
            /***-----------------------------------------------------------------***/

            /***------------------------ ข้อมูลลูกค้าอยู่ก่อนจะทดสอบ ------------------------***/
            career.setCareerName("Student");
            entityManager.persistAndFlush(career);
            customer.setFirstName("Sivaroot");
            customer.setLastName("Chuncharoen");
            customer.setIdNumber("2222222222222");
            customer.setEmail("sivaroot.sut@gmail.com");
            customer.setBirthday(LocalDate.now());
            customer.setPhone("0991230880");
            customer.setAddress(entityManager.persistFlushFind(address));
            customer.setGender(entityManager.persistFlushFind(gender));
            customer.setCareer(entityManager.persistFlushFind(career));
            entityManager.persistAndFlush(customer);
            /***-----------------------------------------------------------------***/

            /***------------------------ ข้อมูลกรมธรรม์อยู่ก่อนจะทดสอบ ------------------------***/

            classProperty.setClassName("Class 1");
            entityManager.persistAndFlush(classProperty);

            propertyPolicy.setClassProperty(entityManager.persistFlushFind(classProperty));
            propertyPolicy.setCostPolicy(700.00);
            propertyPolicy.setDetailProtection("KKKKKKKKKKKKKKK");
            propertyPolicy.setDetailPayment("DDDDDDDDDDDDDDDDD");
            propertyPolicy.setPropertyName("TestPropertyName");
            entityManager.persistFlushFind(propertyPolicy);

            policy.setLicensePlate("กค2018");
            policy.setVin("KIJHYGFVBLOIJHGTF");
            policy.setPeriodStartDate(LocalDate.now());
            policy.setPeriodExpiryDate((LocalDate.now().plusYears(1)));
            policy.setEmployee(employee);
            policy.setCustomer(customer);
            policy.setIssuedDate(LocalDateTime.now());
            policy.setPropertyPolicy(propertyPolicy);
            policy.setCarData(carData);

            entityManager.persistAndFlush(policy);
        }catch (javax.validation.ConstraintViolationException ve){
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        System.out.println("@Before setup data fail -> ConstraintViolationException");
        System.out.println(ve.getConstraintViolations());
        fail("@Before setup data fail -> ConstraintViolationException");
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");

        }catch (javax.persistence.PersistenceException pe){
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        System.out.println("@Before setup data fail -> PersistenceException");
        System.out.println(pe.getLocalizedMessage());
        fail("@Before setup data fail -> PersistenceException");
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }

    //  *********************************************Correct Test*********************************************
    @Test
    public void testCorrect(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("12345678901234567");
        policy.setLicensePlate("ก1234");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            System.out.println("\n\n================================ testCorrect ================================\n\n");
            System.out.println("Result is correct");
            System.out.println("\n\n================================ testCorrect ================================\n\n");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testCorrect ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testCorrect ================================\n\n");
        }
    }
    //  *********************************************Correct Test*********************************************

    //  *********************************************Vin Test*********************************************
    @Test
    public void testVinBeNull(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin(null);
        policy.setLicensePlate("ก1234");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testVinBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testVinBeNull ================================\n\n");
        }
    }
    @Test
    public void testVinTooShort(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("1234");
        policy.setLicensePlate("ก1234");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testVinTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testVinTooShort ================================\n\n");
        }
    }
    @Test
    public void testVinTooLong(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("12345678901234567890");
        policy.setLicensePlate("ก1234");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testVinTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testVinTooLong ================================\n\n");
        }
    }
    @Test
    public void testVinPatternInvalid(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("123456789012345_7");
        policy.setLicensePlate("ก1234");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testVinPatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testVinPatternInvalid ================================\n\n");
        }
    }
    //  *********************************************Vin Test*********************************************

    //  *********************************************LicensePlate Test*********************************************
    @Test
    public void testLicensePlatedBeNull(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("12345678901234567");
        policy.setLicensePlate(null);
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLicensePlatedBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testLicensePlatedBeNull ================================\n\n");
        }
    }
    @Test
    public void testLicensePlatedTooShort(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("12345678901234567");
        policy.setLicensePlate("ก123");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLicensePlatedTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testLicensePlatedTooShort ================================\n\n");
        }
    }
    @Test
    public void testLicensePlatedTooLong(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("12345678901234567");
        policy.setLicensePlate("ก12345");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLicensePlatedTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testLicensePlatedTooLong ================================\n\n");
        }
    }
    @Test
    public void testLicensePlatedPatternInvalid(){
        Policy policy = new Policy();
        policy.setPeriodStartDate(LocalDate.now());
        policy.setPeriodExpiryDate(LocalDate.now().plusYears(5));
        policy.setIssuedDate(LocalDateTime.now());
        policy.setVin("12345678901234567");
        policy.setLicensePlate("ab1234");
        policy.setEmployee(employee);
        policy.setCustomer(customer);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCarData(carData);
        try {
            entityManager.persistAndFlush(policy);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLicensePlatedPatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations().iterator().next().getPropertyPath());
            System.out.println(e.getConstraintViolations().iterator().next().getMessage());
            System.out.println("\n\n================================ testLicensePlatedPatternInvalid ================================\n\n");
        }
    }
    //  *********************************************LicensePlate Test*********************************************
}
