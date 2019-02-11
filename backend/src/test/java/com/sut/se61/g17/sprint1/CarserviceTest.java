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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarserviceTest {

    @Autowired
    private TestEntityManager entityManager;

    Address address = new Address();
    Province province = new Province();
    District district = new District();
    SubDistrict subDistrict = new SubDistrict();
    CarServiceType carServiceType = new CarServiceType();

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

        address.setAddress("7/50 m.6");
        address.setProvince(entityManager.persistFlushFind(province));
        address.setDistrict(district);
        address.setSubDistrict(subDistrict);
        entityManager.persistAndFlush(address);

        carServiceType.setCarServiceType("อู่ซ่อมรถ");
        entityManager.persistAndFlush(carServiceType);
    }
    @Test
    public void testCarserviceTypeCannotBeNull() {
        CarService carService = new CarService();
        carService.setCarServiceType(null);
        carService.setCarServiceName("CPEECP");
        carService.setAddress(address);

        try {
            entityManager.persistAndFlush(carService);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCarserviceTypeCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testCarserviceNameCannotBeNull() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName(null);
        carService.setAddress(address);

        try {
            entityManager.persistAndFlush(carService);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCarserviceNameCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testAddressCannotBeNull() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("CPEECP");
        carService.setAddress(null);

        try {
            entityManager.persistAndFlush(carService);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testAddressCannotBeNull================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testCarserviceNameSizeCannotLowerThanMin() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("C");
        carService.setAddress(address);

        try {
            entityManager.persistAndFlush(carService);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCarserviceNameSizeCannotLowerThanMin================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testCarserviceNameSizeCannotHigherThanMax() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC" +
                "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        carService.setAddress(address);

        try {
            entityManager.persistAndFlush(carService);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCarserviceNameSizeCannotHigherThanMax================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testCarserviceNamePattern() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("หดหฟกฟกฟหกฟ ฟหกฟก กฟหกฟหกฟหกฟหก ฟหฟหกฟหกฟห11225");
        carService.setAddress(address);

        try {
            entityManager.persistAndFlush(carService);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testCarserviceNamePattern================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }

    @Test
    public void testClaimPass() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("CPESUT");
        carService.setAddress(address);

        try {
            entityManager.persistAndFlush(carService);

        } catch (javax.validation.ConstraintViolationException e) {
            fail("Should not pass to this line");
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================testClaimPass================================\n\n");
            System.out.println(e.getConstraintViolations());
        }

    }
    @Test
    public void testUniqueAddress() {
        CarService carService = new CarService();
        carService.setCarServiceType(carServiceType);
        carService.setCarServiceName("CPESUT");
        carService.setAddress(address);
        entityManager.persist(carService);
        entityManager.flush();

        CarService carService1 = new CarService();
        carService1.setCarServiceType(carServiceType);
        carService1.setCarServiceName("CPESUT");
        carService1.setAddress(address);
        try {
            entityManager.persist(carService1);
            entityManager.flush();
            fail("fail");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("\n\n================================ testUniqueCustomerID ================================\n\n");
            System.out.print(e);
        }
    }
}
