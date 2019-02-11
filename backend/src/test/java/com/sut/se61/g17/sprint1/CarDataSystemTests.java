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
public class CarDataSystemTests {


    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;


    BranchCar branchCar = new BranchCar();
    CarType carType = new CarType();
    CarColor carColor = new CarColor();
    GearType gearType = new GearType();

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        carType.setCarType("4-door");
        entityManager.persistAndFlush(carType);
        gearType.setGearType("AUTO");
        entityManager.persistAndFlush(gearType);
        branchCar.setBranchName("HONDA");
        entityManager.persistAndFlush(branchCar);
        carColor.setColor("Titanium");
        entityManager.persistAndFlush(carColor);

    }



    @Test
    public void testModelCannotBeNull() {
        CarData carData = new CarData();
        carData.setModel(null);
        carData.setcC("12345");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);
        try {
            entityManager.persist(carData);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void testCcCannotBeNull() {
        CarData carData = new CarData();
        carData.setModel("e600");
        carData.setcC(null);
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testModelSizeCannotLowerThanMin() {
        CarData carData = new CarData();
        carData.setModel("uu");
        carData.setcC("123456");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testModelSizeCannotHigherThanMax() {
        CarData carData = new CarData();
        carData.setModel("AAAAAAAAAAAAAAAAAAAAAAAAAA");
        carData.setModel("12345690cc");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);
        try {
            entityManager.persist(carData);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }




    @Test
    public void testCcCannotMatchPattern() {
        CarData carData = new CarData();
        carData.setModel("e600");
        carData.setcC("ASDf");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testCarColorCannotBeNull() {
        CarData carData = new CarData();
        carData.setModel("AAAAA");
        carData.setcC("12045");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(null);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCarColorCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testBranchCarCannotBeNull() {
        CarData carData = new CarData();
        carData.setModel("AAAAA");
        carData.setcC("12045");
        carData.setCarType(carType);
        carData.setBranchCar(null);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testBranchCarCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testGearTypeCannotBeNull() {
        CarData carData = new CarData();
        carData.setModel("AAAAA");
        carData.setcC("12045");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(null);

        try {
            entityManager.persist(carData);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testGearTypeCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testCarTypeCannotBeNull() {
        CarData carData = new CarData();
        carData.setModel("AAAAA");
        carData.setcC("12045");
        carData.setCarType(null);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCarTypeCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }


    @Test
    public void testCarDataPass() {
        CarData carData = new CarData();
        carData.setModel("AAAAA");
        carData.setcC("12045");
        carData.setCarType(carType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setGearType(gearType);

        try {
            entityManager.persist(carData);
            entityManager.flush();

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 0);
            System.out.println("*********************testCarDataPass**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testUniqueCustomerID() {
        CarData carData = new CarData();
        carData.setCarType(carType);
        carData.setGearType(gearType);
        carData.setBranchCar(branchCar);
        carData.setCarColor(carColor);
        carData.setModel("AAASSS");
        carData.setcC("12354");
        entityManager.persist(carData);
        entityManager.flush();

        CarData carData1 = new CarData();
        carData1.setCarType(carType);
        carData1.setGearType(gearType);
        carData1.setBranchCar(branchCar);
        carData1.setCarColor(carColor);
        carData1.setModel("AAASSS");
        carData1.setcC("12354");
        entityManager.persist(carData);
        entityManager.flush();
        try {
            entityManager.persist(carData1);
            entityManager.flush();
            fail("fail");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("\n\n================================ Print Error testUniqueCustomerID ================================\n\n");
            System.out.print(e);
        }
    }

}
