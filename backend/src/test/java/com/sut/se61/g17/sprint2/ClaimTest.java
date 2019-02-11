package com.sut.se61.g17.sprint2;

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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClaimTest {

    @Autowired
    private TestEntityManager entityManager;

    Address address = new Address();
    ClaimType claimType = new ClaimType();
    Customer customer = new Customer();
    Province province = new Province();
    District district = new District();
    SubDistrict subDistrict = new SubDistrict();
    CarService carService = new CarService();
    CarServiceType carServiceType = new CarServiceType();
    Gender gender = new Gender();
    Career career = new Career();

    private Validator validator;


    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        province.setProvinceName("สมุทรปราการ");
        entityManager.persistAndFlush(province);
        district.setDistrictName("พระประแดง");
        entityManager.persistAndFlush(district);
        subDistrict.setSubDistrictName("บางหัวเสือ");
        entityManager.persistAndFlush(subDistrict);

        career.setCareerName("Student");
        entityManager.persistAndFlush(career);

        gender.setGenderType("male");
        entityManager.persist(gender);
        entityManager.flush();

        address.setAddress("7/50 m.6");
        address.setProvince(entityManager.persistFlushFind(province));
        address.setDistrict(district);
        address.setSubDistrict(subDistrict);
        entityManager.persistAndFlush(address);

        carServiceType.setCarServiceType("อู่ซ่อมรถ");
        entityManager.persistAndFlush(carServiceType);

        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("CPE");
        carService.setAddress(entityManager.persistAndFlush(address));
        entityManager.persistAndFlush(carService);




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

        claimType.setClaimTypeName("เล็กน้อย");
        entityManager.persistAndFlush(claimType);
    }

    @Test
    public void testCustomerCannotBeNull() {
        Claim claim = new Claim();
        claim.setCustomer(null);
        claim.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCustomerCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testDataAccidentCannotBeNull() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident(null);
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testDataAccidentCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testClaimTypeCannotBeNull() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim.setClaimType(null);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testClaimTypeCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testProvinceCannotBeNull() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim.setClaimType(claimType);
        claim.setProvince(null);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testProvinceCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testCarserviceCannotBeNull() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(null);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCarserviceCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testDataAccidentSizeCannotLowerThanMin() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("กกกกก");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testDataAccidentSizeCannotLowerThanMin================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testDataAccidentSizeCannotHigherThanMax() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก" +
                "กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก" +
                "กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก" +
                "กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testDataAccidentSizeCannotHigherThanMax================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testDataAccidentPattern() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("Abcascsaacs. jfffj .1.900asdasdsadwdasd ");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testDataAccidentPattern================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testClaimtrue() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);

        try {
            entityManager.persistAndFlush(claim);

        } catch (javax.validation.ConstraintViolationException e) {
            fail("Should not pass to this line");
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testClaimtrue================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testUniqueCustomerID() {
        Claim claim = new Claim();
        claim.setCustomer(customer);
        claim.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim.setClaimType(claimType);
        claim.setProvince(province);
        claim.setCarService(carService);
        entityManager.persist(claim);
        entityManager.flush();

        Claim claim1 = new Claim();
        claim1.setCustomer(customer);
        claim1.setDataAccident("รถคว้ำ ที่ตำบลสุรนารี อำเภอเมือง จังหวัดนครราชสีมา เหตุเกิดเมื่อเวลา 19.00");
        claim1.setClaimType(claimType);
        claim1.setProvince(province);
        claim1.setCarService(carService);
        try {
            entityManager.persist(claim1);
            entityManager.flush();
            fail("fail");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("\n\n================================ Print Error testUniqueCustomerID ================================\n\n");
            System.out.print(e);
        }
    }
}
