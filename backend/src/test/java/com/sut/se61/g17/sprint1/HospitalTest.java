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
import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HospitalTest {

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
    Hospital hospital = new Hospital();
    HospitalBranch hospitalBranch = new HospitalBranch();
    HospitalType hospitalType = new HospitalType();


    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();


        hospitalType.setTypeName("รัฐ");
        entityManager.persistAndFlush(hospitalType);




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

    }

    /***------------------------------------------------ Test Conrect ---------------------------------------------------- ***/

    /***----------------------- Sprint 1 ----------------------- ***/
    @Test																/*Sprint 1 */
    public void HospitalBeanchConrect(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(address);



        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();


        } catch (javax.validation.ConstraintViolationException e) {
            fail("fail");
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ HospitalBeanchConrect ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }
    @Test																/*Sprint 1 */
    public void HospitalConrect(){
        Hospital hospital = new Hospital();
        hospital.setHospitalName("มหาราช");
        hospital.setHospitalType(hospitalType);
        try {
            entityManager.persist(hospital);
            entityManager.flush();


        } catch (javax.validation.ConstraintViolationException e) {
            fail("fail");
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ HospitalConrect ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }


    /***------------------------------------------------ Test Null ------------------------------------------------ ***/
    /***----------------------- Sprint 1 ----------------------- ***/
    @Test																/*Sprint 1 */
    public void testBranchNameCanNotNull(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName(null);
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testBranchNameCanNotNull ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testPhoneCanNotNull(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setPhone(null);
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testPhoneCanNotNull ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testHospitalNameCanNotNull(){
        Hospital hospital = new Hospital();
        hospital.setHospitalName(null);
        hospital.setHospitalType(hospitalType);
        try {
            entityManager.persist(hospital);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testHospitalNameCanNotNull ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }
    @Test																/*Sprint 1 */
    public void testHospitalTypeCanNotNull(){
        Hospital hospital = new Hospital();
        hospital.setHospitalName("มหาราช");
        hospital.setHospitalType(null);
        try {
            entityManager.persist(hospital);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testHospitalTypeCanNotNull ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }
    @Test																/*Sprint 1 */
    public void testAdressCanNotNull(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(null);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testAdressCanNotNull ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }


    /***------------------------------------------------ Test Size ------------------------------------------------ ***/
    /***----------------------- Test Size ThenMax ----------------------- ***/

    /***----------------------- Sprint 1 ----------------------- ***/
    @Test																/*Sprint 1 */
    public void testBranchNameSizeThenMax(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testBranchNameSizeThenMax ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testPhoneSizeThenMax(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setPhone("01234567899");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testPhoneSizeThenMax ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testHospitalNameSizeThenMax(){
        Hospital hospital = new Hospital();
        hospital.setHospitalName("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        hospital.setHospitalType(hospitalType);
        try {
            entityManager.persist(hospital);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testHospitalNameSizeThenMax ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }


    /***----------------------- Test Size LowerMin ----------------------- ***/
    /***----------------------- Sprint 1 ----------------------- ***/
    @Test																/*Sprint 1 */
    public void testBranchNameSizeLowerMin(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กก");
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testBranchNameSizeLowerMin ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testPhoneSizeLowerMin(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setPhone("012345678");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testPhoneSizeLowerMin ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testHospitalNameSizeLowerMin(){
        Hospital hospital = new Hospital();
        hospital.setHospitalName("กก");
        hospital.setHospitalType(hospitalType);
        try {
            entityManager.persist(hospital);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testHospitalNameSizeLowerMin ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }

    /***------------------------------------------------ Test Pattern ------------------------------------------------ ***/
    /***----------------------- Sprint 1 ----------------------- ***/
    @Test																/*Sprint 1 */
    public void testBranchNameNotPattern(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("ABCDE");
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testBranchNameNotPattern ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testPhoneNotPattern(){
        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setPhone("012345678A");
        hospitalBranch.setAddress(address);

        try {
            entityManager.persist(hospitalBranch);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testPhoneNotPattern ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }


    }
    @Test																/*Sprint 1 */
    public void testHospitalNameNotPattern(){
        Hospital hospital = new Hospital();
        hospital.setHospitalName("ABCDE");
        hospital.setHospitalType(hospitalType);
        try {
            entityManager.persist(hospital);
            entityManager.flush();
            fail("fail");

        } catch (javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
            System.out.print("\n\n================================ Print Error testHospitalNameNotPattern ================================\n\n");
            System.out.print(e.getConstraintViolations());
        }
    }

    /***------------------------------------------------ Test Unique ------------------------------------------------ ***/
    @Test																/*Sprint 1 */
    public void testUniqueHospitalAddress(){

        Hospital hospital1 = new Hospital();
        hospital1.setHospitalName("ทดสอบ");
        hospital1.setHospitalType(hospitalType);
        entityManager.persistAndFlush(hospital1);

        HospitalBranch hospitalBranch = new HospitalBranch();
        hospitalBranch.setBranchName("กรุงเทพ");
        hospitalBranch.setHospital(hospital1);
        hospitalBranch.setPhone("0123456789");
        hospitalBranch.setAddress(address);

        entityManager.persist(hospitalBranch);
        entityManager.flush();

        HospitalBranch hospitalBranch2 = new HospitalBranch();
        hospitalBranch2.setBranchName("กรุงเทพ");
        hospitalBranch2.setHospital(hospital1);
        hospitalBranch2.setPhone("0123456789");
        hospitalBranch2.setAddress(address);

        try {
            entityManager.persist(hospitalBranch2);
            entityManager.flush();
            fail("fail");
        } catch (javax.persistence.PersistenceException e) {
            System.out.println("\n\n================================ Print Error testUniqueHospitalAddress ================================\n\n");
            System.out.print(e);
        }
    }

}
