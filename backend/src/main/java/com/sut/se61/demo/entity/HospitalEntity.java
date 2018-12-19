package com.example.demo;
import com.sun.istack.internal.NotNull;
import lombok.*;
import javax.annotation.Generated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class HospitalEntity {
    @Id @GeneratedValue
    private Long id;
    private @NotNull String hospitalName;
}
