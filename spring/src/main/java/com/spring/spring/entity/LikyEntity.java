package com.spring.spring.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.spring.spring.entity.primaryKey.LikyPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Like")
@Table(name = "Like")
public class LikyEntity {
    @EmbeddedId
    private LikyPK likyPK;
    private String userProfileUrl;
    private String userNickname;
}
