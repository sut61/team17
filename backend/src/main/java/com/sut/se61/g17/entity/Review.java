package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@Table(name="Review") //ชื่อตาราง
public class Review {
    @Id
    @SequenceGenerator(name = "review_seq", sequenceName = "review_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @Column(name = "REVIEW_ID", unique = true, nullable = false)

    private @NonNull Long reviewID;

    @NotNull
    @Size(min = 3,max = 50)
    @Pattern(regexp = "^[ก-๙]*") // ขึ้นต้นด้วยตัวใหญ่เสมอแล้วตามด้วยอักษรตัวเล็กจะมีก็ได้ไม่มีก็ได้
    private  String comment;


//    @Column(unique = true)
    @NotNull
    @Size(min = 3,max = 50)
    @Pattern(regexp = "^[ก-๙]*")
    private  String cons;

    @ManyToOne
    @JoinColumn(name = "classID", nullable = false)
    private ClassProperty classProperty;

    @ManyToOne                  //joinความสัมพันธ์ *-1
    @JoinColumn(name = "statusID", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "CARDATA_ID")
    private CarData carData;

    public Review() {
    }

    public Review(String comment, String cons, ClassProperty classProperty, Status status, CarData carData) {
        this.comment = comment;
        this.cons = cons;
        this.classProperty = classProperty;
        this.status = status;
        this.carData = carData;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ClassProperty getClassProperty() {
        return classProperty;
    }

    public void setClassProperty(ClassProperty classProperty) {
        this.classProperty = classProperty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public CarData getCarData() {
        return carData;
    }

    public void setCarData(CarData carData) {
        this.carData = carData;
    }
}