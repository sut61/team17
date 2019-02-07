package com.sut.se61.g17.controller;

import com.sut.se61.g17.entity.ClassProperty;
import com.sut.se61.g17.entity.PropertyPolicy;
import com.sut.se61.g17.repository.ClassPropertyRepository;
import com.sut.se61.g17.repository.PropertyPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/propertyPolicy")
public class PropertyPolicyController {
    @Autowired
    private PropertyPolicyRepository propertyPolicyRepository;

    @Autowired
    private ClassPropertyRepository classPropertyRepository;


    @GetMapping(path = "/")
    public Collection<PropertyPolicy> getPropertyPolicy(){
        return propertyPolicyRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/{propertyID}")
    public PropertyPolicy getOnePropertyPolicy(@PathVariable Long propertyID){
        return propertyPolicyRepository.findById(propertyID).get();
    }

    @GetMapping(path = "/classProperty")
    public Collection<ClassProperty> getClassProperty(){
        return classPropertyRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/classProperty/{classID}")
    public ClassProperty getOneClassProperty(@PathVariable Long classID){
       return classPropertyRepository.findById(classID).get();
    }

    @PostMapping(path = "/{classID}")
    public PropertyPolicy postPropertyPolicy(@RequestBody PropertyPolicy propertyPolicy,
                                             @PathVariable Long classID ){
        ClassProperty classProperty1 = classPropertyRepository.findById(classID).get();

        propertyPolicy.setClassProperty(classProperty1);

        return propertyPolicyRepository.save(propertyPolicy);
    }


}
