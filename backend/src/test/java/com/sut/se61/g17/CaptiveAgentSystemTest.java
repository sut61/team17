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
public class CaptiveAgentSystemTest {

    @Autowired
    private TestEntityManager entityManager;

    Address address = new Address();
    Province province = new Province();
    District district = new District();
    SubDistrict subDistrict = new SubDistrict();
    Gender gender = new Gender();
    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

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
    }

/*
    ===== Name of method =====
    test @NotNull use keyword "BeNull"
    test @Size min use keyword "TooShort" , max use keyword "TooLong"
    test @Pattern use keyword "PatternInvalid"
    test @Column(unique = true) use keyword "NotUnique"
    test @Email use keyword "EmailIncorrect"
    test @Past use keyword "NotPast"
 */

//  *********************************************Correct Test*********************************************
    @Test
    public void testCorrect(){
        Employee employee = new Employee();
        employee.setUsername("test1234");
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setLastName("efgh");
        employee.setIdNumber("1234567890123");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        try {
            entityManager.persistAndFlush(employee);
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

//  *********************************************Username Test*********************************************
    public Employee setAllExceptUsername(){
        Employee employee = new Employee();
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setLastName("efgh");
        employee.setIdNumber("1234567890123");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testUsernameBeNull(){
        Employee employee = setAllExceptUsername();
        employee.setUsername(null);
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testUsernameBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testUsernameBeNull ================================\n\n");
        }
    }
    @Test
    public void testUsernameTooShort(){
        Employee employee = setAllExceptUsername();
        employee.setUsername("t");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testUsernameTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testUsernameTooShort ================================\n\n");
        }
    }
    @Test
    public void testUsernameTooLong(){
        Employee employee = setAllExceptUsername();
        String s = "";
        for (int i=1;i<=25;i++){
            s+= "a";
        }
        employee.setUsername(s);
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testUsernameTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testUsernameTooLong ================================\n\n");
        }
    }
    @Test
    public void testUsernamePatternInvalid(){
        Employee employee = setAllExceptUsername();
        employee.setUsername("0test");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testUsernamePatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testUsernamePatternInvalid ================================\n\n");
        }
    }
    @Test
    public void testUsernameNotUnique(){
        Employee employee1 = new Employee();
        employee1.setUsername("test");
        employee1.setPassword("test");
        employee1.setFirstName("abcd");
        employee1.setLastName("efgh");
        employee1.setIdNumber("1234567890123");
        employee1.setEmail("test@gmail.com");
        employee1.setBirthday(LocalDate.now().minusYears(18));
        employee1.setPhone("0123456789");
        employee1.setAddress(address);
        employee1.setGender(gender);
        entityManager.persistAndFlush(employee1);

        Employee employee2 = new Employee();
        employee2.setUsername("test");
        employee2.setPassword("test");
        employee2.setFirstName("abcd");
        employee2.setLastName("efgh");
        employee2.setIdNumber("1234567890123");
        employee2.setEmail("test@gmail.com");
        employee2.setBirthday(LocalDate.now().minusYears(18));
        employee2.setPhone("0123456789");
        employee2.setAddress(address);
        employee2.setGender(gender);
        try {
            entityManager.persistAndFlush(employee2);
            fail("Should not pass to this line");
        }catch (javax.persistence.PersistenceException e){
            System.out.println("\n\n================================ testUsernameNotUnique ================================\n\n");
            System.out.println(e.getMessage());
            System.out.println("\n\n================================ testUsernameNotUnique ================================\n\n");
        }
    }
//  *********************************************Username Test*********************************************

//  *********************************************Password Test*********************************************
    @Test
    public void testPasswordBeNull(){
    Employee employee = new Employee();
    employee.setUsername("test");
    employee.setPassword(null);
    employee.setFirstName("abcd");
    employee.setLastName("efgh");
    employee.setIdNumber("1234567890123");
    employee.setEmail("test@gmail.com");
    employee.setBirthday(LocalDate.now().minusYears(18));
    employee.setPhone("0123456789");
    employee.setAddress(address);
    employee.setGender(gender);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testPasswordBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testPasswordBeNull ================================\n\n");
    }
}
//  *********************************************Password Test*********************************************

//  *********************************************FirstName Test*********************************************
    public Employee setAllExceptFirstName(){
        Employee employee = new Employee();
        employee.setUsername("test");
        employee.setPassword("test");
        employee.setLastName("efgh");
        employee.setIdNumber("1234567890123");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testFirstNameBeNull(){
    Employee employee = setAllExceptFirstName();
    employee.setFirstName(null);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testFirstNameBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testFirstNameBeNull ================================\n\n");
    }
}
    @Test
    public void testFirstNameTooShort(){
        Employee employee = setAllExceptFirstName();
        employee.setFirstName("a");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testFirstNameTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testFirstNameTooShort ================================\n\n");
        }
    }
    @Test
    public void testFirstNameTooLong(){
        Employee employee = setAllExceptFirstName();
        String s = "";
        for (int i=1;i<=35;i++){
            s+= "a";
        }
        employee.setFirstName(s);
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testFirstNameTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testFirstNameTooLong ================================\n\n");
        }
    }
    @Test
    public void testFirstNamePatternInvalid(){
        Employee employee = setAllExceptFirstName();
        employee.setFirstName("123abc");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testFirstNamePatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testFirstNamePatternInvalid ================================\n\n");
        }
    }
//  *********************************************FirstName Test*********************************************

//  *********************************************LastName Test*********************************************
    public Employee setAllExceptLastname(){
        Employee employee = new Employee();
        employee.setUsername("test");
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setIdNumber("1234567890123");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testLastNameBeNull(){
        Employee employee = setAllExceptLastname();
        employee.setLastName(null);
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLastNameBeNull ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testLastNameBeNull ================================\n\n");
        }
    }
    @Test
    public void testLastNameTooShort(){
        Employee employee = setAllExceptLastname();
        employee.setLastName("e");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLastNameTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testLastNameTooShort ================================\n\n");
        }
    }
    @Test
    public void testLastNameTooLong(){
        Employee employee = setAllExceptLastname();
        String s = "";
        for (int i=1;i<=35;i++){
            s+= "a";
        }
        employee.setLastName(s);
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLastNameTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testLastNameTooLong ================================\n\n");
        }
    }
    @Test
    public void testLastNamePatternInvalid(){
        Employee employee = setAllExceptLastname();
        employee.setLastName("123efgh");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testLastNamePatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testLastNamePatternInvalid ================================\n\n");
        }
    }
//  *********************************************LastName Test*********************************************

//  *********************************************IdNumber Test*********************************************
    public Employee setAllExceptIdNumber(){
        Employee employee = new Employee();
        employee.setUsername("test");
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setLastName("efgh");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testIdNumberBeNull(){
    Employee employee = setAllExceptIdNumber();
    employee.setIdNumber(null);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testIdNumberBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testIdNumberBeNull ================================\n\n");
    }
}
    @Test
    public void testIdNumberTooShort(){
        Employee employee = setAllExceptIdNumber();
        employee.setIdNumber("1");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testIdNumberTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testIdNumberTooShort ================================\n\n");
        }
    }
    @Test
    public void testIdNumberTooLong(){
        Employee employee = setAllExceptIdNumber();
        employee.setIdNumber("123456789012345");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testIdNumberTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testIdNumberTooLong ================================\n\n");
        }
    }
    @Test
    public void testIdNumberPatternInvalid(){
        Employee employee = setAllExceptIdNumber();
        employee.setIdNumber("abc1234567890");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testIdNumberPatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testIdNumberPatternInvalid ================================\n\n");
        }
    }
    @Test
    public void testIdNumberNotUnique(){
        Employee employee1 = new Employee();
        employee1.setUsername("test1");
        employee1.setPassword("test");
        employee1.setFirstName("abcd");
        employee1.setLastName("efgh");
        employee1.setIdNumber("1234567890123");
        employee1.setEmail("test@gmail.com");
        employee1.setBirthday(LocalDate.now().minusYears(18));
        employee1.setPhone("0123456789");
        employee1.setAddress(address);
        employee1.setGender(gender);
        entityManager.persistAndFlush(employee1);

        Employee employee2 = new Employee();
        employee2.setUsername("test2");
        employee2.setPassword("test");
        employee2.setFirstName("abcd");
        employee2.setLastName("efgh");
        employee2.setIdNumber("1234567890123");
        employee2.setEmail("test@gmail.com");
        employee2.setBirthday(LocalDate.now().minusYears(18));
        employee2.setPhone("0123456789");
        employee2.setAddress(address);
        employee2.setGender(gender);
        try {
            entityManager.persistAndFlush(employee2);
            fail("Should not pass to this line");
        }catch (javax.persistence.PersistenceException e){
            System.out.println("\n\n================================ testIdNumberNotUnique ================================\n\n");
            System.out.println(e.getMessage());
            System.out.println("\n\n================================ testIdNumberNotUnique ================================\n\n");
        }
    }
//  *********************************************IdNumber Test*********************************************

//  *********************************************Email Test*********************************************
    public Employee setAllExceptEmail(){
        Employee employee = new Employee();
        employee.setUsername("test");
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setLastName("efgh");
        employee.setIdNumber("1234567890123");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testEmailBeNull(){
    Employee employee = setAllExceptEmail();
    employee.setEmail(null);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testEmailBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testEmailBeNull ================================\n\n");
    }
}
    @Test
    public void testEmailIncorrect(){
        Employee employee = setAllExceptEmail();
        employee.setEmail("test.com");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testEmailIncorrect ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testEmailIncorrect ================================\n\n");
        }
    }
//  *********************************************Email Test*********************************************

//  *********************************************Birthday Test*********************************************
    public Employee setAllExceptBirthday(){
        Employee employee = new Employee();
        employee.setUsername("test");
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setLastName("efgh");
        employee.setIdNumber("1234567890123");
        employee.setEmail("test@gmail.com");
        employee.setPhone("0123456789");
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testBirthdayBeNull(){
    Employee employee = setAllExceptBirthday();
    employee.setBirthday(null);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testBirthdayBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testBirthdayBeNull ================================\n\n");
    }
}
    @Test
    public void testBirthdayNotPast(){
        Employee employee = setAllExceptBirthday();
        employee.setBirthday(LocalDate.now());
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testBirthdayNotPast ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testBirthdayNotPast ================================\n\n");
        }
    }
//  *********************************************Birthday Test*********************************************

//  *********************************************Phone Test*********************************************
    public Employee setAllExceptPhone(){
        Employee employee = new Employee();
        employee.setUsername("test");
        employee.setPassword("test");
        employee.setFirstName("abcd");
        employee.setLastName("efgh");
        employee.setIdNumber("1234567890123");
        employee.setEmail("test@gmail.com");
        employee.setBirthday(LocalDate.now().minusYears(18));
        employee.setAddress(address);
        employee.setGender(gender);
        return employee;
    }
    @Test
    public void testPhoneBeNull(){
    Employee employee = setAllExceptPhone();
    employee.setPhone(null);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testPhoneBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testPhoneBeNull ================================\n\n");
    }
}
    @Test
    public void testPhoneTooShort(){
        Employee employee = setAllExceptPhone();
        employee.setPhone("0");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testPhoneTooShort ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testPhoneTooShort ================================\n\n");
        }
    }
    @Test
    public void testPhoneTooLong(){
        Employee employee = setAllExceptPhone();
        employee.setPhone("01234568901234");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testPhoneTooLong ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testPhoneTooLong ================================\n\n");
        }
    }
    @Test
    public void testPhonePatternInvalid(){
        Employee employee = setAllExceptPhone();
        employee.setPhone("A123456890");
        try {
            entityManager.persistAndFlush(employee);
            fail("Should not pass to this line");
        }catch (javax.validation.ConstraintViolationException e){
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.println("\n\n================================ testPhonePatternInvalid ================================\n\n");
            System.out.println(e.getConstraintViolations());
            System.out.println("\n\n================================ testPhonePatternInvalid ================================\n\n");
        }
    }
//  *********************************************Phone Test*********************************************

//  *********************************************Address Test*********************************************
    @Test
    public void testAddressBeNull(){
    Employee employee = new Employee();
    employee.setUsername("test");
    employee.setPassword("test");
    employee.setFirstName("abcd");
    employee.setLastName("efgh");
    employee.setIdNumber("1234567890123");
    employee.setEmail("test@gmail.com");
    employee.setBirthday(LocalDate.now().minusYears(18));
    employee.setPhone("0123456789");
    employee.setAddress(null);
    employee.setGender(gender);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testAddressBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testAddressBeNull ================================\n\n");
    }
}
//  *********************************************Address Test*********************************************

//  *********************************************Gender Test*********************************************
    @Test
    public void testGenderBeNull(){
    Employee employee = new Employee();
    employee.setUsername("test");
    employee.setPassword("test");
    employee.setFirstName("abcd");
    employee.setLastName("efgh");
    employee.setIdNumber("1234567890123");
    employee.setEmail("test@gmail.com");
    employee.setBirthday(LocalDate.now().minusYears(18));
    employee.setPhone("0123456789");
    employee.setAddress(address);
    employee.setGender(null);
    try {
        entityManager.persistAndFlush(employee);
        fail("Should not pass to this line");
    }catch (javax.validation.ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        assertEquals(violations.isEmpty(), false);
        assertEquals(violations.size(), 1);
        System.out.println("\n\n================================ testGenderBeNull ================================\n\n");
        System.out.println(e.getConstraintViolations());
        System.out.println("\n\n================================ testGenderBeNull ================================\n\n");
    }
}
//  *********************************************Gender Test*********************************************

}
