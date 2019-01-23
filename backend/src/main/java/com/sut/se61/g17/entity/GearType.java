package com.sut.se61.g17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class GearType {
    @Id
    @SequenceGenerator(name="gearType_seq",sequenceName="gearType_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gearType_seq")
    @Column(name="gearTypeID",unique = true, nullable = false)
    private @NonNull Long gearTypeID;
    private @NonNull String gearType;

    public GearType(@NonNull String gearType) {
        this.gearType = gearType;
    }

    public Long getGearTypeID() {
        return gearTypeID;
    }

    public void setGearTypeID(Long gearTypeID) {
        this.gearTypeID = gearTypeID;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }
}
