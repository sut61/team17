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
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeneficiarySystemTests {



	/***
	 * Run command : mvnw test -Dtest=BeneficiarySystemTests
	 * Line 189 : Test Null
	 * Line 374 : Test Pattern
	 * Line 468 : Test Size
	 * Line 515 : Test Unique
	 * ***/

	@Autowired
	private TestEntityManager entityManager;

	/* Other related tables */

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
	Relationship relationship = new Relationship();
	private Validator validator;



	@Before
	public void setup() {


		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
        /***--------------------------------------------------------------------------------------------------***/
        /***--------------------------------------------------------------------------------------------------***/
        /***--------------------------------------------------------------------------------------------------***/
		/***------------------------------------- Set related table data -------------------------------------***/
        /***--------------------------------------------------------------------------------------------------***/
        /***--------------------------------------------------------------------------------------------------***/
        /***--------------------------------------------------------------------------------------------------***/

        try {


            gender.setGenderType("male");
            entityManager.persist(gender);
            entityManager.flush();


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

            relationship.setRelationship("Father");
            entityManager.persistAndFlush(relationship);
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



	/***----------------------- Test Null ----------------------- ***/

	@Test
	public void testFirstnameCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname(null);
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894561231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testFirstnameCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}



	}

	@Test
	public void testLastnameCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname(null);
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894561231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testLastnameCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	@Test
	public void testPhoneCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone(null);
		beneficiary.setPersonalId("7894561231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testPhoneCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}
	@Test
	public void testPersonalIdCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId(null);
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testPersonalIdCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	@Test
	public void testGenderCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894561231123");
		beneficiary.setGender(null);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testGenderCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}
	@Test
	public void testPolicyCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894561231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(null);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testPolicyCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}
	@Test
	public void testRelationshipCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894571231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(null);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testRelationshipCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}
	@Test
	public void testAddressCannotBeNull(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894161231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(null);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================Print Error testAddressCannotBeNull() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	/***----------------------- Test Pattern ----------------------- ***/

	@Test
	public void testFirstnameMustBePatternCorrectly(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("sIvaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894161231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================Print Error testFirstnameMustBePatternCorrectly() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	@Test
	public void testLastnameMustBePatternCorrectly(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("cHuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894161231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================Print Error testLastnameMustBePatternCorrectly() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}
	@Test
	public void testPhoneMustBePatternCorrectly(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("X991230X80");
		beneficiary.setPersonalId("7894161231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================Print Error testPhoneMustBePatternCorrectly() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	@Test
	public void testPersonalIdMustBePatternCorrectly(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("X8X4161231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================Print Error testPersonalIdMustBePatternCorrectly() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}


	/***----------------------- Test Size ----------------------- ***/
	@Test
	public void testPersonalIDSizeMustBeOverMin(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================Print Error  testPersonalIDSizeMustBeOverMin() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	@Test
	public void testPersonalIDSizeMustBeLessThanMax(){
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("777777777777777777777777777777777777777");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		try {
			entityManager.persistAndFlush(beneficiary);
			fail("Should not pass to this line");
		}catch (javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n================================ Print Error testPersonalIDSizeMustBeLessThanMax() ================================\n\n");
			System.out.println(e.getConstraintViolations());
		}
	}

	/***----------------------- Test Unique ----------------------- ***/

	@Test
	public void testUniqueConstraint(){

		/*** @UniqueConstraint(columnNames={"firstname", "lastname","personalId"}) ***/
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setFirstname("Sivaroot");
		beneficiary.setLastname("Chuncharoen");
		beneficiary.setPhone("0991230880");
		beneficiary.setPersonalId("7894571231123");
		beneficiary.setGender(gender);
		beneficiary.setPolicy(policy);
		beneficiary.setRelationship(relationship);
		beneficiary.setAddress(address);
		entityManager.persistAndFlush(beneficiary);


		Beneficiary beneficiary1 = new Beneficiary();
		beneficiary1.setFirstname("Sivaroot");
		beneficiary1.setLastname("Chuncharoen");
		beneficiary1.setPhone("0991230880");
		beneficiary1.setPersonalId("7894571231123");
		beneficiary1.setGender(gender);
		beneficiary1.setPolicy(policy);
		beneficiary1.setRelationship(relationship);
		beneficiary1.setAddress(address);

		try {
			entityManager.persistAndFlush(beneficiary1);
			fail("Should not pass to this line");
		}catch (javax.persistence.PersistenceException e){

			System.out.println("\n\n================================ Print Error testUniqueConstraint() ================================\n\n");
			System.out.println(e.getLocalizedMessage());
		}
	}

}

