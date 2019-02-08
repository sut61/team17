package com.sut.se61.g17;


import com.sut.se61.g17.entity.ClassProperty;
import com.sut.se61.g17.entity.PropertyPolicy;
import com.sut.se61.g17.repository.PropertyPolicyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
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
public class PropertyPolicyTests {


    @Autowired
    private PropertyPolicyRepository propertyPolicyRepository;

    @Autowired
    private TestEntityManager entityManager;

    ClassProperty classProperty = new ClassProperty();

    private Validator validator;


    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        classProperty.setClassName("Class 1");
        entityManager.persistAndFlush(classProperty);


    }

    @Test
    public void testProperty() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdef");
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(1500);
        propertyPolicy.setClassProperty(classProperty);

        entityManager.flush();
        System.out.println("***************test All*************");

    }

    @Test
    public void testPropertyNameCannotBeNull() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName(null);
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(1500);
        propertyPolicy.setClassProperty(classProperty);


        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("********************************test Name***********************");
            System.out.println(e);
        }
    }
    @Test
    public void testDetailProtectionCannotBeNull() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdef");
        propertyPolicy.setDetailProtection(null);
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(1500);
        propertyPolicy.setClassProperty(classProperty);


        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("********************************test DetailProtectionCannotBeNull ***********************");
            System.out.println(e);
        }
    }
    @Test
    public void testDetailPaymentCannotBeNull() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdef");
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment(null);
        propertyPolicy.setCostPolicy(1500);
        propertyPolicy.setClassProperty(classProperty);


        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("********************************test DetailPaymentCannotBeNull ***********************");
            System.out.println(e);
        }
    }



    @Test
    public void testPropertyNameBeUnique() {
        PropertyPolicy p1 = new PropertyPolicy();
        p1.setPropertyName("Abcdef");
        p1.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        p1.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        p1.setCostPolicy(1500);
        p1.setClassProperty(classProperty);
        entityManager.persistAndFlush(p1);

        PropertyPolicy p2 = new PropertyPolicy();
        p2.setPropertyName("Abcdef");
        p2.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        p2.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        p2.setCostPolicy(1500);
        p2.setClassProperty(classProperty);
        try {
            entityManager.persistAndFlush(p2);
            fail("Should not pass to this line");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("********************************test unique*************************");
            System.out.println(e.getLocalizedMessage());

        }

    }

    @Test
    public void testproprtyNameMoreThanMax() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdefghijklmnopqrstuvwxyzabcdefg");
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(1500);
        propertyPolicy.setClassProperty(classProperty);

        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("********************************test Property name more than Max********************");
            System.out.println(e);
        }
    }

    @Test
    public void testproprtyNameFistNotChar() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("8abcd");
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(1500);

        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("********************************test Property name first not char********************");
            System.out.println(e);
        }
    }

    @Test
    public void testDetailPaymentLessThanMin() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdef");
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment("Abcd");
        propertyPolicy.setCostPolicy(1500);

        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("********************************test detailPayment min********************");
            System.out.println(e.getConstraintViolations());
        }
    }

    @Test
    public void testDetailProtectionLessThanMin() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdef");
        propertyPolicy.setDetailProtection("Abcd");
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(1500);

        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("********************************test detailProtection min********************");
            System.out.println(e.getConstraintViolations());
        }
    }


    @Test
    public void testCostPolicyLessThanValue() {
        PropertyPolicy propertyPolicy = new PropertyPolicy();
        propertyPolicy.setPropertyName("abcdef");
        propertyPolicy.setDetailProtection("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setDetailPayment("Abcdefghijklmnopqrstuvwxyz");
        propertyPolicy.setCostPolicy(50);

        try {
            entityManager.persist(propertyPolicy);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
            System.out.println("********************************test cost************************");
            System.out.println(e);
        }
    }


}
