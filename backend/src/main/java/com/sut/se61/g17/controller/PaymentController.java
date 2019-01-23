package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.*;
import com.sut.se61.g17.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceStatusRepository invoiceStatusRepository;
    @Autowired
    private PaymentRepositoy paymentRepositoy;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;


    @PostMapping("/pay/{invoiceID}/{empUser}")
    public Payment payInvoice(@RequestBody Payment payment,
                              Invoice invoice,
                              Employee employee,
                              @PathVariable Long invoiceID,
                              @PathVariable String empUser) throws Exception{


        invoice = invoiceRepository.findById(invoiceID).get();
        System.out.println(payment.getAmount());
        System.out.println(invoice.getInvoiceAmount());

        if(payment.getAmount().doubleValue() != invoice.getInvoiceAmount().doubleValue())
            throw new Exception("The amount is not correct");
        Date date = new Date();
        employee = employeeRepository.findByUsername(empUser);
        invoice.setInvoiceStatus(invoiceStatusRepository.findByStatus("Paid"));
        invoiceRepository.save(invoice);
        payment.setPayDate(date);
        payment.setInvoice(invoice);
        payment.setEmployee(employee);
        return paymentRepositoy.save(payment);
    }

    @GetMapping("/search-policy/{policyID}")
    public Policy getOnePolicy(@PathVariable Long policyID) {
        return policyRepository.findById(policyID).get();
    }

    @GetMapping("search-invoice-all/{policyID}")
    public List<Invoice> getInvoice(@PathVariable Long policyID) {
        return invoiceRepository.findByPolicy_PolicyID(policyID);
    }

    @GetMapping("search-invoice-overdue/{policyID}")
    public List<Invoice> getInvoiceOverdue(@PathVariable Long policyID) {
        return invoiceRepository.findByPolicy_PolicyIDAndAndInvoiceStatus_Status(policyID, "Not Paid");
    }

    @Scheduled(fixedRate = 15000)
    public void InvoiceManagement() {
        System.out.println(" @Scheduled : " + new Date());
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Bangkok"));
        InvoiceStatus invoiceS = invoiceStatusRepository.findByStatus("Not Paid");
        for (Policy policy : policyRepository.findAll()) {
            if(!invoiceRepository.existsByPolicy(policy)) {
                if (localDate.isBefore(policy.getPeriodExpiryDate()) && localDate.getDayOfMonth() == policy.getPeriodStartDate().getDayOfMonth()) {
                    Invoice invoice = new Invoice(localDate, 645.5, invoiceS, policy);
                    System.out.println(invoiceRepository.save(invoice).getInvoiceID());
                }

            }
        }
    }
    @GetMapping("/test")
    public Collection<District> getAllDistrict(){
        Province province = provinceRepository.findByProvinceName("กรุงเทพมหานคร");
        return districtRepository.findAllByProvinces(province);
    }
}
