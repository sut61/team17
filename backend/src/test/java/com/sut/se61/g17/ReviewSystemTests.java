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
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewSystemTests {


    @Autowired
    private TestEntityManager entityManager;

    ClassProperty classProperty = new ClassProperty();
    Status status = new Status();
    CarData carData = new CarData();
    BranchCar branchCar = new BranchCar();
    CarType carType = new CarType();
    CarColor carColor = new CarColor();
    GearType gearType = new GearType();

    private Validator validator;
    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        classProperty.setClassName("ประกันภัยรถประเภท 1");
        entityManager.persistAndFlush(classProperty);

        status.setStatus("Good");
        entityManager.persistAndFlush(status);

        carType.setCarType("4-door");
        entityManager.persistAndFlush(carType);
        gearType.setGearType("AUTO");
        entityManager.persistAndFlush(gearType);
        branchCar.setBranchName("HONDA");
        entityManager.persistAndFlush(branchCar);
        carColor.setColor("Titanium");
        entityManager.persistAndFlush(carColor);
        carData.setcC("500cc");
        carData.setModel("CITY 2020");
        carData.setBranchCar(entityManager.persistFlushFind(branchCar));
        carData.setCarColor(entityManager.persistFlushFind(carColor));
        carData.setCarType(entityManager.persistFlushFind(carType));
        carData.setGearType(entityManager.persistFlushFind(gearType));
        entityManager.persistAndFlush(carData);

    }
    @Test
    public void testCommentCannotBeNull() {
        Review review = new Review();
        review.setComment(null);
        review.setCons(1);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(carData);

        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testCommentSizeCannotLowerThanMin() {
        Review review = new Review();
        review.setComment("ก");
        review.setCons(1);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(carData);
        try {
            entityManager.persist(review);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentSizeCannotLowerThanMin**********************");
            System.out.println(e);
        }
    }

    @Test
    public void testConsCannotLowerThanMin() {
        Review review = new Review();
        review.setComment("ทดสอบ");
        review.setCons(0);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(carData);
        try {
            entityManager.persist(review);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testConsCannotLowerThanMin**********************");
            System.out.println(e);
        }
    }


    @Test
    public void testCommentSizeCannotHigherThanMax() {
        Review review = new Review();
        review.setComment("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        review.setCons(2);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(carData);
        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentSizeCannotHigherThanMax**********************");
            System.out.println(e);
        }
    }

    @Test
    public void testConsCannotHigherThanMax() {
        Review review = new Review();
        review.setComment("กกกกกกกกก");
        review.setCons(6);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(carData);
        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testConsCannotHigherThanMax**********************");
            System.out.println(e);
        }
    }

    @Test
    public void testCommentCannotMatchPattern() {
        Review review = new Review();
        review.setComment("Aกกกกกกกกกกกกกกกกกกก");
        review.setCons(1);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(carData);

        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCommentCannotMatchPattern**********************");
            System.out.println(e);
        }
    }



    @Test
    public void testClassPropertyCannotBeNull() {
        Review review = new Review();
        review.setComment("ทดสอบ");
        review.setCons(1);
        review.setClassProperty(null);
        review.setStatus(status);
        review.setCarData(carData);

        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testClassPropertyCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testStatusCannotBeNull() {
        Review review = new Review();
        review.setComment("ทดสอบ");
        review.setCons(1);
        review.setClassProperty(classProperty);
        review.setStatus(null);
        review.setCarData(carData);

        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testStatusCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testCardataCannotBeNull() {
        Review review = new Review();
        review.setComment("ทดสอบ");
        review.setCons(1);
        review.setClassProperty(classProperty);
        review.setStatus(status);
        review.setCarData(null);

        try {
            entityManager.persist(review);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("*********************testCardataCannotBeNull**********************");
            System.out.println(e.getConstraintViolations());
        }
    }

}
