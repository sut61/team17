package com.sut.se61.g17.sprint1;

import com.sut.se61.g17.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentTests {
    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;



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
    Invoice invoice = new Invoice();
    InvoiceStatus invoiceStatus = new InvoiceStatus();

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        try {

            /*** ------------------------ เพิ่มข้อมูลเพศ่อนจะทดสอบ -------------------------***/

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
            carData.setcC("500cc");
            carData.setModel("CITY 2020");
            carData.setBranchCar(entityManager.persistFlushFind(branchCar));
            carData.setCarColor(entityManager.persistFlushFind(carColor));
            carData.setCarType(entityManager.persistFlushFind(carType));
            carData.setGearType(entityManager.persistFlushFind(gearType));
            entityManager.persistAndFlush(carData);
            /***-----------------------------------------------------------------***/

            /***------------------------ ข้อมูลรถอยู่ก่อนจะทดสอบ ------------------------***/
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

            invoiceStatus.setStatus("Paid");
            entityManager.persistAndFlush(invoiceStatus);


            invoice.setPolicy(policy);
            invoice.setInvoiceDate(LocalDate.now());
            invoice.setInvoiceAmount(1500.00);
            invoice.setInvoiceStatus(invoiceStatus);
            entityManager.persistAndFlush(invoice);

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

    /**
     * Amount Test in entity Payment
     * - CannotBeNull
     * - Min
     * - Max
     *
    ***/

    @Test
    public void testPayment_AmountCannotBeNull(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(invoice);
        payment.setAmount(null);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail ->testPayment_AmountCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail ->testPayment_AmountCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }

    @Test
    public void testPayment_AmountValueMustBeOverMin(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(invoice);
        payment.setAmount(10.0);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testPayment_AmountSizeMustBeOverMin()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testPayment_AmountSizeMustBeOverMin()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testPayment_AmountValueMustBeLessThanMax(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(invoice);
        payment.setAmount(10000.0);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testPayment_AmountSizeMustBeLessThanMax()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testPayment_AmountSizeMustBeLessThanMax()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testPayment_payDateCannotBeNull(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(invoice);
        payment.setAmount(1000.0);
        payment.setPayDate(null);

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testPayment_payDateCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testPayment_payDateCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testPayment_InvoiceCannotBeNull(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(null);
        payment.setAmount(1000.0);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testPayment_InvoiceCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testPayment_InvoiceCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testPayment_EmployeeCannotBeNull(){
        Payment payment = new Payment();
        payment.setEmployee(null);
        payment.setInvoice(invoice);
        payment.setAmount(1000.0);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testPayment_EmployeeCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testPayment_EmployeeCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }

    /**
     * Amount Test in entity InvoiceStatus
     * - CannotBeNull
     * - Min
     * - Max
     * - Pattern
     ***/



    @Test
    public void testInvoiceStatus_StatusCannotBeNull(){
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setStatus(null);

        try {
            entityManager.persistAndFlush(invoiceStatus);
            fail("@Test data fail -> testInvoiceStatus_StatusCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoiceStatus_StatusCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }

    @Test
    public void testInvoiceStatus_StatusSizeMustBeOverMin(){
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setStatus("Pai");

        try {
            entityManager.persistAndFlush(invoiceStatus);
            fail("@Test data fail -> testInvoiceStatus_StatusSizeMustBeOverMin()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoiceStatus_StatusSizeMustBeOverMin()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoiceStatus_StatusSizeMustBeLessThanMax(){
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setStatus("Nottt Paid");

        try {
            entityManager.persistAndFlush(invoiceStatus);
            fail("@Test data fail -> testInvoiceStatus_StatusSizeMustBeLessThanMax()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoiceStatus_StatusSizeMustBeLessThanMax()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoiceStatus_StatusMustBePatternCorrectly(){
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setStatus("paid");

        try {
            entityManager.persistAndFlush(invoiceStatus);
            fail("@Test data fail -> testInvoiceStatus_StatusMustBePatternCorrectly()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoiceStatus_StatusMustBePatternCorrectly()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }

    /**
     * Amount Test in entity Invoice
     * - CannotBeNull
     * - Min
     * - Max
     *
     ***/

    @Test
    public void testInvoice_invoiceDateCannotBeNull(){
        Invoice invoice1 = new Invoice();
        invoice1.setPolicy(policy);
        invoice1.setInvoiceDate(null);
        invoice1.setInvoiceAmount(1500.00);
        invoice1.setInvoiceStatus(invoiceStatus);


        try {
            entityManager.persistAndFlush(invoice1);
            fail("@Test data fail -> testInvoice_invoiceDateCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoice_invoiceDateCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoice_invoiceAmountCannotBeNull(){
        Invoice invoice1 = new Invoice();
        invoice1.setPolicy(policy);
        invoice1.setInvoiceDate(LocalDate.now());
        invoice1.setInvoiceAmount(null);
        invoice1.setInvoiceStatus(invoiceStatus);


        try {
            entityManager.persistAndFlush(invoice1);
            fail("@Test data fail -> testInvoice_invoiceAmountCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoice_invoiceAmountCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoice_PolicyCannotBeNull(){
        Invoice invoice1 = new Invoice();
        invoice1.setPolicy(null);
        invoice1.setInvoiceDate(LocalDate.now());
        invoice1.setInvoiceAmount(1500.0);
        invoice1.setInvoiceStatus(invoiceStatus);


        try {
            entityManager.persistAndFlush(invoice1);
            fail("@Test data fail -> testInvoice_PolicyCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoice_PolicyCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoice_InvoiceStatusCannotBeNull(){
        Invoice invoice1 = new Invoice();
        invoice1.setPolicy(policy);
        invoice1.setInvoiceDate(LocalDate.now());
        invoice1.setInvoiceAmount(1500.00);
        invoice1.setInvoiceStatus(null);


        try {
            entityManager.persistAndFlush(invoice1);
            fail("@Test data fail -> testInvoice_InvoiceStatusCannotBeNull()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoice_InvoiceStatusCannotBeNull()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoice_invoiceAmountValueMustBeOverMin(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(invoice);
        payment.setAmount(10.0);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testInvoice_invoiceAmountValueMustBeOverMin()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoice_invoiceAmountValueMustBeOverMin()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
    @Test
    public void testInvoice_invoiceAmountValueMustBeLessThanMax(){
        Payment payment = new Payment();
        payment.setEmployee(employee);
        payment.setInvoice(invoice);
        payment.setAmount(10000.0);
        payment.setPayDate(new Date());

        try {
            entityManager.persistAndFlush(payment);
            fail("@Test data fail -> testInvoice_invoiceAmountValueMustBeLessThanMax()");
        }catch (javax.validation.ConstraintViolationException ve){
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("@Test data fail -> testInvoice_invoiceAmountValueMustBeLessThanMax()");
            System.out.println(ve.getConstraintViolations());
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
            System.out.println("/***--------------------------------------------------------------------------------------------------***/");
        }
    }
}
