package com.sut.se61.g17.entity;
import javax.persistence.*;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class CarService {
    @Id
    @SequenceGenerator(name="user_seq",sequenceName="user_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
    @Column(name="TITLE_ID",unique = true, nullable = false)
    private @NotNull Long carServiceID;
    private String nameCarService;
    private Long dataAddress;

    public Long getDataAddress() {
        return dataAddress;
    }

    public void setDataAddress(Long dataAddress) {
        this.dataAddress = dataAddress;
    }

    public Long getCarServiceID() {
        return carServiceID;
    }

    public void setCarServiceID(Long carServiceID) {
        this.carServiceID = carServiceID;
    }

    public String getNameCarService() {
        return nameCarService;
    }

    public void setNameCarService(String nameCarService) {
        this.nameCarService = nameCarService;
    }

    public CarService(String nameCarService){this.nameCarService = nameCarService;}
}
