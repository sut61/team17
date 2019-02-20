package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "EMPLOYEE_ID")
    private Long employeeID;


    @NotNull(message="Please type username before save!")
    @Size(min = 2, max = 20,message = "Username length incorrect!(2-20)")
    @Pattern(regexp = "[A-Za-z]\\w*",message = "Username incorrect!")
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull(message = "Please type firstName before save!")
    @Size(min = 2, max = 30,message = "FirstName length incorrect!(2-30)")
    @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*",message = "FirstName incorrect!")
    private String firstName;

    @NotNull(message = "Please type lastName before save!")
    @Size(min = 2, max = 30,message = "LastName length incorrect!(2-30)")
    @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*",message = "LastName incorrect!")
    private String lastName;

    @NotNull(message = "Please type idNumber before save!")
    @Size(min = 13, max = 13,message = "IdNumber length incorrect!(13)")
    @Pattern(regexp = "\\d+",message = "IdNumber incorrect!")
    @Column(unique = true)
    private String idNumber;

    @NotNull(message = "Please type email before save!")
    @Email(message = "Email incorrect!")
    private String email ;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "Please type phone before save!")
    @Size(min = 10, max = 10,message = "Phone length incorrect!(10)")
    @Pattern(regexp = "[0]\\d*",message = "Phone incorrect!")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "addressID")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "GENDER_ID")
    private Gender gender;

    public Employee(@NotNull(message = "Please type username before save!") @Size(min = 2, max = 20, message = "Username length incorrect!(2-20)") @Pattern(regexp = "[A-Za-z]\\w*", message = "Username incorrect!") String username, @NotNull String password, @NotNull(message = "Please type firstName before save!") @Size(min = 2, max = 30, message = "FirstName length incorrect!(2-30)") @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*", message = "FirstName incorrect!") String firstName, @NotNull(message = "Please type lastName before save!") @Size(min = 2, max = 30, message = "LastName length incorrect!(2-30)") @Pattern(regexp = "[A-Za-zก-ฮ][A-Za-zก-์]*", message = "LastName incorrect!") String lastName, @NotNull(message = "Please type idNumber before save!") @Size(min = 13, max = 13, message = "IdNumber length incorrect!(13)") @Pattern(regexp = "\\d+", message = "IdNumber incorrect!") String idNumber, @NotNull(message = "Please type email before save!") @Email(message = "Email incorrect!") String email, LocalDate birthday, @NotNull(message = "Please type phone before save!") @Size(min = 10, max = 10, message = "Phone length incorrect!(10)") @Pattern(regexp = "[0]\\d*", message = "Phone incorrect!") String phone, Address address, Gender gender) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
}
