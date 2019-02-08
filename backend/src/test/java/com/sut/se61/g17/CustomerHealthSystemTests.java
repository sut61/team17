package com.sut.se61.g17;

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
public class CustomerHealthSystemTests {

    @Autowired
    private TestEntityManager entityManager;

    Gender gender = new Gender();
    Address address = new Address();
    Province province = new Province();
    District district = new District();
    SubDistrict subDistrict = new SubDistrict();
    Customer customer = new Customer();
    Career career = new Career();
    Disease disease = new Disease();


    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        disease.setDiseaseName("โรคภูมิแพ้");
        entityManager.persistAndFlush(disease);

        gender.setGenderType("male");
        entityManager.persist(gender);
        entityManager.flush();

        province.setProvinceName("สมุทรปราการ");
        entityManager.persistAndFlush(province);
        district.setDistrictName("พระประแดง");
        entityManager.persistAndFlush(district);
        subDistrict.setSubDistrictName("บางหัวเสือ");
        entityManager.persistAndFlush(subDistrict);


        address.setAddress("165/5");
        address.setProvince(entityManager.persistFlushFind(province));
        address.setDistrict(district);
        address.setSubDistrict(subDistrict);
        entityManager.persistAndFlush(address);

        career.setCareerName("Student");
        entityManager.persistAndFlush(career);
        customer.setFirstName("Chantardsak");
        customer.setLastName("Usaha");
        customer.setIdNumber("1111111111111");
        customer.setEmail("chantardsak@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPhone("0882103942");
        customer.setAddress(entityManager.persistFlushFind(address));
        customer.setGender(entityManager.persistFlushFind(gender));
        customer.setCareer(entityManager.persistFlushFind(career));
        entityManager.persistAndFlush(customer);

    }

    /***----------------------- Test Conrect ----------------------- ***/
    @Test																/*Sprint 2 */
    public void testCustomerHealthConrect() {
        CustomerHealth customerHealth = new CustomerHealth();
        customerHealth.setAge(20);
        customerHealth.setHeight(180);
        customerHealth.setWeight(60);
        customerHealth.setVivisection("ไม่เคย");
        customerHealth.setMedicine("ไม่มี");
        customerHealth.setDisease(disease);
        customerHealth.setCustomer(customer);

        try {
            entityManager.persist(customerHealth);
            entityManager.flush();


        } catch (javax.validation.ConstraintViolationException e) {
            fail("fail");
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ testCustomerHealthConrect ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }


    /***----------------------- Test Null ----------------------- ***/
    @Test
    public void testVivisectionCanNotNull() {
        CustomerHealth customerHealth = new CustomerHealth();
        customerHealth.setAge(20);
        customerHealth.setHeight(180);
        customerHealth.setWeight(60);
        customerHealth.setVivisection(null);
        customerHealth.setMedicine("ไม่มี");
        customerHealth.setDisease(disease);
        customerHealth.setCustomer(customer);
        try {
            entityManager.persist(customerHealth);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testVivisectionCanNotNull ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }


    /***----------------------- Test Size ----------------------- ***/
    @Test
    public void testVivisectionSizeThenMax() {
        CustomerHealth customerHealth = new CustomerHealth();
        customerHealth.setAge(20);
        customerHealth.setHeight(180);
        customerHealth.setWeight(60);
        customerHealth.setVivisection("ไม่เคย111111111111111111112222222222222222222222222222222222");
        customerHealth.setMedicine("ไม่มี");
        customerHealth.setDisease(disease);
        customerHealth.setCustomer(customer);
        try {
            entityManager.persist(customerHealth);
            entityManager.flush();
            fail("fail");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testVivisectionSizeThenMax ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }
    @Test
    public void testVivisectionSizelowerMin() {
        CustomerHealth customerHealth = new CustomerHealth();
        customerHealth.setAge(20);
        customerHealth.setHeight(180);
        customerHealth.setWeight(60);
        customerHealth.setVivisection("ม");
        customerHealth.setMedicine("ไม่มี");
        customerHealth.setDisease(disease);
        customerHealth.setCustomer(customer);
        try {
            entityManager.persist(customerHealth);
            entityManager.flush();
            fail("fail");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testVivisectionSizelowerMin ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }

    /***----------------------- Test Pattern ----------------------- ***/
    @Test
    public void testVivisectionNotPattern() {
        CustomerHealth customerHealth = new CustomerHealth();
        customerHealth.setAge(20);
        customerHealth.setHeight(180);
        customerHealth.setWeight(60);
        customerHealth.setVivisection("ABCD");
        customerHealth.setMedicine("ไม่มี");
        customerHealth.setDisease(disease);
        customerHealth.setCustomer(customer);
        try {
            entityManager.persist(customerHealth);
            entityManager.flush();
            fail("fail");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testVivisectionNotPattern ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }
    @Test
    public void testUniqueCustomerID() {
        CustomerHealth customerHealth = new CustomerHealth();
        customerHealth.setAge(20);
        customerHealth.setHeight(180);
        customerHealth.setWeight(60);
        customerHealth.setVivisection("ไม่เคย");
        customerHealth.setMedicine("ไม่มี");
        customerHealth.setDisease(disease);
        customerHealth.setCustomer(customer);
        entityManager.persist(customerHealth);
        entityManager.flush();

        CustomerHealth customerHealth2 = new CustomerHealth();
        customerHealth2.setAge(20);
        customerHealth2.setHeight(180);
        customerHealth2.setWeight(60);
        customerHealth2.setVivisection("ไม่เคย");
        customerHealth2.setMedicine("ไม่มี");
        customerHealth2.setDisease(disease);
        customerHealth2.setCustomer(customer);
        try {
            entityManager.persist(customerHealth2);
            entityManager.flush();
            fail("fail");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("\n\n================================ Print Error testUniqueCustomerID ================================\n\n");
            System.out.print(e);
        }
    }



}






