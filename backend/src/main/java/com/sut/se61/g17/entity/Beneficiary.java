package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"firstname", "lastname","personalId"})
)
@NoArgsConstructor
public class Beneficiary {
    @Id
    @SequenceGenerator(name = "beneficiary_seq",sequenceName = "beneficiary_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "beneficiary_seq")
    @Column(name = "beneficiary_Id")
    private Long beneficiaryId;

    @NotNull
    @Size(min = 1,max = 25)
    @Pattern(regexp = "^[A-Z][a-z]*")
    @Column(name = "firstname")
    private String firstname;

 //   @NotNull
    @Size(min = 1,max = 25)
    @Pattern(regexp = "^[A-Z][a-z]*")
    @Column(name = "lastname")
    private String lastname;

 //   @NotNull
    @Size(min = 8,max = 10)
    @Pattern(regexp = "^[0]\\d*")
    private String phone;

    @NotNull
    @Size(min = 13,max = 13)
    @Pattern(regexp = "\\d*")
    @Column(/*name = "personalId",*/unique = true)
    private String personalId;

   // @NotNull
    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;

   // @NotNull
    @ManyToOne
    @JoinColumn(name = "POLICY_ID")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "relationship_id")
    private Relationship relationship;

    @OneToOne
    @JoinColumn(name = "addressID")
    private Address address;



}
