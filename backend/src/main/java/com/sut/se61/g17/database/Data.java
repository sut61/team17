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
    private PropertyPolicyRepository propertyPolicyRepository;
    @Autowired
    private ClassPropertyRepository classPropertyRepository;

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private ClaimTypeRepository claimTypeRepository;
    @Autowired
    private CarDataRepository carDataRepository;
    @Autowired
    private RelationshipRepository relationshipRepository;
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
        ClassPropertyData();
        carServiceTypeData();
        hospitalTypeData();
        gearType();
        Status();
        propertyTypeData();
        carDataTest();
        getClaimData();
        relationshipData();
        PolicyTest();

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
        employee.setFirstName("Sivaroot");
        employee.setLastName("Chuncharoen");
        employee.setGender(male);
        employee.setAddress(addressRepository.saveAndFlush(new Address("7/50 ม.7",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ"))));
        employee.setIdNumber("1111111111111");
        employee.setBirthday(LocalDate.now());
        employee.setPhone("0991230880");
        employee.setEmail("sivaroot@gmail.com");
        employeeRepository.saveAndFlush(employee);
    }

    private void careerData() {
        careerRepository.saveAndFlush(new Career("Student"));
        careerRepository.saveAndFlush(new Career("Engineering"));
        careerRepository.saveAndFlush(new Career("Teacher"));
        careerRepository.saveAndFlush(new Career("government officer"));
    }

    public void ClassPropertyData() {
        classPropertyRepository.saveAndFlush(new ClassProperty("ประกันภัยรถชั้น 1"));
        classPropertyRepository.saveAndFlush(new ClassProperty("ประกันภัยรถชั้น 2+"));
        classPropertyRepository.saveAndFlush(new ClassProperty("ประกันภัยรถชั้น 3+"));
        classPropertyRepository.saveAndFlush(new ClassProperty("ประกันภัยรถชั้น 3"));
    }
    private void getClaimData(){
        claimTypeRepository.save(new ClaimType("เล็กน้อย"));
        claimTypeRepository.save(new ClaimType("ปานกลาง"));
        claimTypeRepository.save(new ClaimType("ร้ายแรง"));
    }

    private void invoiceStatusData() {
        invoiceStatusRepository.saveAndFlush(new InvoiceStatus("Not Paid"));
        invoiceStatusRepository.saveAndFlush(new InvoiceStatus("Paid"));
    }

    private void genderData() {
        genderRepository.saveAndFlush(new Gender("male"));
        genderRepository.saveAndFlush(new Gender("female"));
    }

    private void Status() {
        Status status1 = new Status();
        status1.setStatus("Good");
        statusRepository.save(status1);

        Status status2 = new Status();
        status2.setStatus("Bad");
        statusRepository.save(status2);
    }


    private void carColor() {
        carColorRepository.saveAndFlush(new CarColor("Red"));
        carColorRepository.saveAndFlush(new CarColor("Black"));
        carColorRepository.saveAndFlush(new CarColor("White"));
        carColorRepository.saveAndFlush(new CarColor("Titanium Flash"));
    }

    private void carType() {
        carTypeRepository.saveAndFlush(new CarType("4-door"));
        carTypeRepository.saveAndFlush(new CarType("3-door"));
        carTypeRepository.saveAndFlush(new CarType("2-door"));
    }

    private void branchCar() {
        branchCarRepository.save(new BranchCar("MAZDA"));
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


   public void propertyTypeData() {
        propertyPolicyRepository.save(new PropertyPolicy("ThaiCareYou","คุ้มครองในวงเงิน 100,000 บาท" ,"จ่ายผ่านบัตรเครดิตได้ทุกประเภท" , 1990.00 , classPropertyRepository.findByClassName("ประกันภัยรถชั้น 1")));
    }
    public void carDataTest() {
        carDataRepository.save(new CarData("gram","500",branchCarRepository.findByBranchName("HONDA"),
                carColorRepository.findByColor("Black"),carTypeRepository.findByCarType("4-door"),
                gearTypeRepository.findByGearType("Auto")));
    }
    public void relationshipData(){
        relationshipRepository.save(new Relationship("adoptive parents"));
        relationshipRepository.save(new Relationship("adopted child"));
        relationshipRepository.save(new Relationship("aunt"));
        relationshipRepository.save(new Relationship("brother"));
        relationshipRepository.save(new Relationship("sister"));
        relationshipRepository.save(new Relationship("father"));
        relationshipRepository.save(new Relationship("mother"));


    }
    public void PolicyTest(){
        Employee employee = employeeRepository.findByUsername("admin");

        Address address = addressRepository.saveAndFlush(new Address("7/50 ม.6",
                districtRepository.findByDistrictName("พระประแดง"),
                provinceRepository.findByProvinceName("สมุทรปราการ"),
                subDistrictRepository.findBySubDistrictName("บางหัวเสือ")));

        CarData carData = carDataRepository.save(
                new CarData(
                        "CX-3 2016",
                        "2000",
                        branchCarRepository.findByBranchName("MAZDA"),
                        carColorRepository.findByColor("Titanium Flash"),carTypeRepository.findByCarType("4-door"),
                        gearTypeRepository.findByGearType("Auto")));
        Customer customer = customerRepository.saveAndFlush(new Customer(
                "Sivaroot",
                "Chuncharoen",
                "1231231231231",
                "sivaroot.sut@gmail.com",
                LocalDate.now(ZoneId.of("Asia/Bangkok")),
                "0991230880",
                address,
                genderRepository.findByGenderType("male"),
                careerRepository.findByCareerName("Student")));

       PropertyPolicy propertyPolicy = propertyPolicyRepository.save(new PropertyPolicy("MasterInsurance","คุ้มครองในวงเงิน 100,000 บาท" ,"จ่ายผ่านบัตรเครดิตได้ทุกประเภท" , 1990.00 , classPropertyRepository.findByClassName("ประกันภัยรถชั้น 1")));

        Policy policy = new Policy();
        policy.setLicensePlate("กด2018");
        policy.setVin("VIUNHUYTGBVFDS14L");
        policy.setPolicyID(1L);
        policy.setIssuedDate(LocalDateTime.now(ZoneId.of("Asia/Bangkok")));
        policy.setPeriodStartDate(LocalDate.now(ZoneId.of("Asia/Bangkok")));
        policy.setPeriodExpiryDate((LocalDate.now(ZoneId.of("Asia/Bangkok")).plusYears(1)));
        policy.setEmployee(employee);
        policy.setPropertyPolicy(propertyPolicy);
        policy.setCustomer(customer);
        policy.setIssuedDate(LocalDateTime.now(ZoneId.of("Asia/Bangkok")));
        policy.setCarData(carData);

        policyRepository.save(policy);
    }
}



