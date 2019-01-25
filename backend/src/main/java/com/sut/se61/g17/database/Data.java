package com.sut.se61.g17.database;


import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Component
public class Data implements CommandLineRunner {

    private final String FILE_ENCODE = "UTF-8";
    private final String filePath = "src/externaldata/Thailand-UTF8 Demo.txt";

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private SubDistrictRepository subDistrictRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private InvoiceStatusRepository invoiceStatusRepository;
    @Autowired
    private CarColorRepository carColorRepository;
    @Autowired
    private CarTypeRepository carTypeRepository;
    @Autowired
    private BranchCarRepository branchCarRepository;
    @Autowired
    private CarServiceTypeRepository carServiceTypeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;

    @Autowired
    private GearTypeRepository gearTypeRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CarDataRepository carDataRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Data(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
        addressData();
        genderData();
        invoiceStatusData();
        employeeData();
        careerData();
        carColor();
        carType();
        branchCar();

        carServiceTypeData();
        hospitalTypeData();
//        paymentDemoData();
        gearType();
        propertyTypeData();
        carDataTest();

    }

    private void addressData() {
        try {
            String data;
            BufferedReader dataIns = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), FILE_ENCODE));
            System.out.println("Start");
            Set<District> districts = new HashSet<>();
            Set<SubDistrict> subDistricts = new HashSet<>();
            String tempProvince = "";
            String tempDistrict = "";
            while ((data = dataIns.readLine()) != null) {
                String[] dataAry = data.split("\\t");
                if (dataAry.length != 1) {


                    if (provinceRepository.findByProvinceName(dataAry[0]) == null)
                        provinceRepository.save(new Province(dataAry[0]));
                    if (districtRepository.findByDistrictName(dataAry[1]) == null)
                        districtRepository.save(new District(dataAry[1]));
                    if (subDistrictRepository.findBySubDistrictName(dataAry[2]) == null)
                        subDistrictRepository.save(new SubDistrict(dataAry[2]));
                    Province province = provinceRepository.findByProvinceName(dataAry[0]);
                    District district = districtRepository.findByDistrictName(dataAry[1]);
                    SubDistrict subDistrict = subDistrictRepository.findBySubDistrictName(dataAry[2]);

                    if (!tempProvince.equals(dataAry[0])) {
                        provinceRepository.save(province);
                        districtRepository.save(district);
                        districts = new HashSet<>();
                        subDistricts = new HashSet<>();
                    }
                    if (!tempDistrict.equals(dataAry[1])) {
                        districtRepository.save(district);
                        subDistricts = new HashSet<>();
                    }

                    districts.add(district);
                    subDistricts.add(subDistrict);
                    province.setDistricts(districts);
                    district.setSubDistricts(subDistricts);

                    System.out.println(province.getProvinceName() + " " + district.getDistrictName() + " " + subDistrict.getSubDistrictName());
                    tempProvince = dataAry[0];
                    tempDistrict = dataAry[1];
                }
            }
            dataIns.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void employeeData() {
        Gender male = genderRepository.findByGenderType("male");
        Employee employee = new Employee();
        employee.setUsername("admin");
        employee.setPassword(bCryptPasswordEncoder.encode("admin"));
        employee.setFirstName("First Name Test");
        employee.setLastName("Last Name Test");
        employee.setGender(male);
        employeeRepository.saveAndFlush(employee);
    }

    private void careerData() {
        careerRepository.saveAndFlush(new Career("Student"));
        careerRepository.saveAndFlush(new Career("Engineering"));
        careerRepository.saveAndFlush(new Career("Teacher"));
        careerRepository.saveAndFlush(new Career("government officer"));
    }


    private void invoiceStatusData() {
        invoiceStatusRepository.saveAndFlush(new InvoiceStatus("Not Paid"));
        invoiceStatusRepository.saveAndFlush(new InvoiceStatus("Paid"));
    }

    private void genderData() {
        genderRepository.saveAndFlush(new Gender("male"));
        genderRepository.saveAndFlush(new Gender("female"));
    }

    private void carColor() {
        carColorRepository.saveAndFlush(new CarColor("Red"));
        carColorRepository.saveAndFlush(new CarColor("Black"));
        carColorRepository.saveAndFlush(new CarColor("White"));
    }

    private void carType() {
        carTypeRepository.saveAndFlush(new CarType("4-door"));
        carTypeRepository.saveAndFlush(new CarType("3-door"));
        carTypeRepository.saveAndFlush(new CarType("2-door"));
    }

    private void branchCar() {
        branchCarRepository.save(new BranchCar("BMW"));
        branchCarRepository.save(new BranchCar("HONDA"));
        branchCarRepository.save(new BranchCar("BENZ"));
        branchCarRepository.save(new BranchCar("LAMBOGINI"));
    }

    private void gearType() {
        gearTypeRepository.save(new GearType("Auto"));
        gearTypeRepository.save(new GearType("Manual"));
    }

    private void carServiceTypeData() {
        carServiceTypeRepository.saveAndFlush(new CarServiceType("อู่บริการ"));
        carServiceTypeRepository.saveAndFlush(new CarServiceType("ศูนย์บริการ"));
    }

    private void hospitalTypeData() {
        hospitalTypeRepository.saveAndFlush(new HospitalType("รัฐ"));
        hospitalTypeRepository.saveAndFlush(new HospitalType("เอกชน"));
    }


   /* private void paymentDemoData() throws ParseException {

        LocalDateTime dateTimeNow = LocalDateTime.now(ZoneId.of("Asia/Bangkok"));

        LocalDate dateNow = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        LocalDate dateExpiry = dateNow.plusYears(2);

        Policy policy = new Policy();


        Address address = addressRepository.saveAndFlush(new Address("7/50 ม.6",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ")));

        Customer customer = customerRepository.saveAndFlush(new Customer(
                "Cusfirstname",
                "Cuslastname",
                "1201478954123",
                "email@gmail.com",
                dateNow,
                "0991230880",
                address,
                genderRepository.findByGenderType("male"),
                careerRepository.findByCareerName("Student")));

        policy.setCarData(carDataRepository.)
        policy.setPeriodStartDate(dateNow);
        policy.setPeriodExpiryDate(dateExpiry);
        policy.setIssuedDate(dateTimeNow);
        policy.setEmployee(employeeRepository.findByUsername("admin"));
        policy.setCustomer(customer);
        policyRepository.save(policy);

    }*/

    public void propertyTypeData() {
        propertyRepository.save(new Property("ชั้นที่ 1"));
    }
    public void carDataTest() {
        carDataRepository.save(new CarData("gram","500",branchCarRepository.findByBranchName("HONDA"),
                carColorRepository.findByColor("Black"),carTypeRepository.findByCarType("4-door"),
                gearTypeRepository.findByGearType("Auto")));
    }
}



