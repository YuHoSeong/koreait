package com.spring.spring.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
//? 복합키 클래스 생성 방법
@Embeddable
public class LikyPK implements Serializable {
    
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "board_number")
    private int boardNumber;
}
