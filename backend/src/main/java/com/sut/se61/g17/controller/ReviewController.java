package com.sut.se61.g17.controller;


import com.sut.se61.g17.entity.CarData;
import com.sut.se61.g17.entity.ClassProperty;
import com.sut.se61.g17.entity.Review;
import com.sut.se61.g17.entity.Status;

import com.sut.se61.g17.repository.CarDataRepository;
import com.sut.se61.g17.repository.ClassPropertyRepository;
import com.sut.se61.g17.repository.ReviewRepository;
import com.sut.se61.g17.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ClassPropertyRepository classPropertyRepository;

    @Autowired
    private CarDataRepository carDataRepository;


    @GetMapping("/review")
    public Collection<Review> Review() {
        return reviewRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/status")
    public Collection<Status> getStatus() {
        return statusRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/classProperty")
    public Collection<ClassProperty> getClassProperty() {
        return classPropertyRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/carData")
    public Collection<CarData> getCarData(){
        return carDataRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/review/{classID}/{carID}/{statusID}")
    public Review newReviews(@RequestBody Review review,  @PathVariable Long carID ,@PathVariable Long classID, @PathVariable Long statusID){
        ClassProperty classProperty = classPropertyRepository.findById(classID).get();

        CarData carData = carDataRepository.findById(carID).get();
        Status status = statusRepository.findById(statusID).get(); //เตรียม object มา join

//        Review newReviews = new Review();

        review.setClassProperty(classProperty);
        review.setCarData(carData);
        review.setStatus(status);
//        review.setComment("Aaaaaab");
        return reviewRepository.save(review);
    }


}
