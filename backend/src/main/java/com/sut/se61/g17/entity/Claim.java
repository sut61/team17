package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
// is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the fields
@Entity
@NoArgsConstructor
public class Claim {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "claimID", unique = true, nullable = false)
    private @NotNull Long claimID;

    @NotNull
    @Size(min = 30, max = 100)
    @Pattern(regexp = "^([ก-๙]|[0-9]|[ ./*\\-])+")
    private String dataAccident;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PROVINCE_ID")
    private Province province;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "claimtypeID")
    private ClaimType claimType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID",unique = true)
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "carServiceID")
    private CarService carService;


}
