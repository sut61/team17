package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*@NoArgsConstructor*/
@Data
@Entity
public class PropertyPolicy {
    @Id
    @SequenceGenerator(name = "property_seq",sequenceName = "property_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "property_seq")
    @Column(name = "PROPERTY_ID")
    private @NotNull Long propertyID;

    @Column(unique = true )
    @Pattern(regexp = "^[A-Za-z ก-๙]\\w+")
    @Size(min = 5,max = 30)
    @NotNull
    private String propertyName;

    @Size(min = 10 , max = 512)
    @NotNull
    private String detailProtection ;

    @Size(min = 10 , max = 512)
    @NotNull
    private String detailPayment ;



    @Min(value = 100)
    @NotNull
    private Double costPolicy ;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "classID", nullable = false)
    private ClassProperty classProperty;

    public PropertyPolicy() {
    }

    public PropertyPolicy(@Pattern(regexp = "^[A-Za-z]\\w+") @Size(min = 5, max = 30) @NotNull String propertyName, @Size(min = 10, max = 512) @NotNull String detailProtection, @Size(min = 10, max = 512) @NotNull String detailPayment, @Min(value = 100) @NotNull double costPolicy, ClassProperty classProperty) {
        this.propertyName = propertyName;
        this.detailProtection = detailProtection;
        this.detailPayment = detailPayment;
        this.costPolicy = costPolicy;
        this.classProperty = classProperty;
    }

    public Long getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(Long propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDetailProtection() {
        return detailProtection;
    }

    public void setDetailProtection(String detailProtection) {
        this.detailProtection = detailProtection;
    }

    public String getDetailPayment() {
        return detailPayment;
    }

    public void setDetailPayment(String detailPayment) {
        this.detailPayment = detailPayment;
    }

    public double getCostPolicy() {
        return costPolicy;
    }

    public void setCostPolicy(double costPolicy) {
        this.costPolicy = costPolicy;
    }

    public ClassProperty getClassProperty() {
        return classProperty;
    }

    public void setClassProperty(ClassProperty classProperty) {
        this.classProperty = classProperty;
    }
}
