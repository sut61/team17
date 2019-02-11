package com.sut.se61.g17.sprint1;

import com.sut.se61.g17.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private TestEntityManager entityManager;

    Address address = new Address();
    Gender gender = new Gender();
    Career career = new Career();
    Customer customer = new Customer();
    Province province = new Province();
    District district = new District();
    SubDistrict subDistrict = new SubDistrict();


    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        career.setCareerName("Student");
        entityManager.persistAndFlush(career);
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1231231231231");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0966202695");


        gender.setGenderType("male");
        entityManager.persist(gender);
        entityManager.flush();


        province.setProvinceName("สมุทรปราการ");
        entityManager.persistAndFlush(province);
        district.setDistrictName("พระประแดง");
        entityManager.persistAndFlush(district);
        subDistrict.setSubDistrictName("บางหัวเสือ");
        entityManager.persistAndFlush(subDistrict);

        address.setAddress("95/8 m.8");
        address.setProvince(entityManager.persistFlushFind(province));
        address.setDistrict(district);
        address.setSubDistrict(subDistrict);
        entityManager.persistAndFlush(address);


    }

    /***----------------------- Test Pass All ----------------------- ***/
    @Test
    public void testCorrect(){
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);
        try {
            entityManager.persistAndFlush(customer);
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

    /***----------------------- Test Null ----------------------- ***/

    @Test
    public void testCustomerFirstNameCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName(null);
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testFirstNameBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testFirstNameBeNull ================================\n\n");
        }
    }


    @Test
    public void testCustomerLastNameCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName(null);
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLastNameBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testLastNameBeNull ================================\n\n");
        }
    }

    @Test
    public void testIdNumberCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber(null);
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testIdNumberCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testIdNumberCannotBeNull ================================\n\n");
        }
    }


    @Test
    public void testEmailCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail(null);
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testEmailCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testEmailCannotBeNull ================================\n\n");
        }
    }


    @Test
    public void testBirthdayCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(null);
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testBirthdayCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testBirthdayCannotBeNull ================================\n\n");
        }
    }


    @Test
    public void testPhoneCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone(null);
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testPhoneCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testPhoneCannotBeNull ================================\n\n");
        }
    }


    @Test
    public void testGenderCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(null);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testGenderCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testGenderCannotBeNull ================================\n\n");
        }
    }

    @Test
    public void testCareerCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(null);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testCareerCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testCareerCannotBeNull ================================\n\n");
        }
    }

    @Test
    public void testAddressCannotBeNull() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(null);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testAddressCannotBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testAddressCannotBeNull ================================\n\n");
        }
    }

/***----------------------- Test Pattern ----------------------- ***/

@Test
    public void testFirstNameMustBePatternCorrectly() {
        Customer customer = new Customer();
        customer.setFirstName("nAtthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testFirstNameMustBePatternCorrectly ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testFirstNameMustBePatternCorrectly ================================\n\n");
        }
    }

    @Test
    public void testLastNameMustBePatternCorrectly() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("aNo");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLastNameMustBePatternCorrectly ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testLastNameMustBePatternCorrectly ================================\n\n");
        }
    }


    @Test
    public void testIdNumberMustBePatternCorrectly() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("XX345678901X3");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testIdNumberMustBePatternCorrectly ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testIdNumberMustBePatternCorrectly ================================\n\n");
        }
    }


    /***----------------------- Test Size ----------------------- ***/


    @Test
    public void testIdNumberSizeMustBeOverMin() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testPersonalIDSizeMustBeOverMin ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testPersonalIDSizeMustBeOverMin ================================\n\n");
        }
    }

    @Test
    public void testIdNumberSizeMustBeLessThanMax() {
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("12345678901234567890");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);


        try {
            entityManager.persistAndFlush(customer);
            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testPersonalIDSizeMustBeOverMin ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testPersonalIDSizeMustBeOverMin ================================\n\n");
        }
    }

    /***----------------------- Test Unique ----------------------- ***/

    @Test
    public void testUniqueConstraint(){

        /*** @UniqueConstraint(columnNames={"firstname", "lastname","IdNumber"}) ***/
        Customer customer = new Customer();
        customer.setFirstName("Natthapol");
        customer.setLastName("Ano");
        customer.setIdNumber("1234567890123");
        customer.setEmail("natthapol@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0123456789");
        customer.setGender(gender);
        customer.setCareer(career);
        customer.setAddress(address);
        entityManager.persistAndFlush(customer);


        Customer customer1 = new Customer();
        customer1.setFirstName("Natthapol");
        customer1.setLastName("Ano");
        customer1.setIdNumber("1234567890123");
        customer1.setEmail("natthapol@gmail.com");
        customer1.setBirthday(LocalDate.now());
        customer1.setPhone("0123456789");
        customer1.setGender(gender);
        customer1.setCareer(career);
        customer1.setAddress(address);

        try {
            entityManager.persistAndFlush(customer1);
            fail("Should not pass to this line");
        }catch (javax.persistence.PersistenceException e){

            System.out.println("\n\n================================ Print Error testUniqueConstraint() ================================\n\n");
            System.out.println(e.getLocalizedMessage());
        }
    }

}


