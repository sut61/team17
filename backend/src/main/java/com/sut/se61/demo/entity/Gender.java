package com.sut.se61.demo.entity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Gender {
    @Id @GeneratedValue
    private @NonNull Long genderID;
    private  String genderName;

    public Long getGenderID() {
        return genderID;
    }

    public void setGenderID(Long genderID) {
        this.genderID = genderID;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Gender(String genderName) {
        this.genderName = genderName;
    }

}
