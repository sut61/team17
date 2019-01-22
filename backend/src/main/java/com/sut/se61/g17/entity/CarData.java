package com.sut.se61.g17.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
//@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class CarData {
    @Id 
    @GeneratedValue
    private @NonNull Long id;
    private @NonNull String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

