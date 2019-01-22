package com.sut.se61.g17.entity;
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
public class Hospital {
    @Id @GeneratedValue
    private Long id;
    private @NotNull String hospitalName;
}
