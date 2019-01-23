package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.Province;
import com.sut.se61.g17.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private ProvinceRepository provinceRepository;

    @GetMapping(path = "/province-test")
    public Province getProvince(){
        return provinceRepository.findById(1L).get();
    }
}
