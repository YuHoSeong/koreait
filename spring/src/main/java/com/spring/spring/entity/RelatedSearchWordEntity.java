package com.spring.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RelatedSearchWord")
@Table(name = "RelatedSearchWord")
public class RelatedSearchWordEntity {
    @Id
    private int sequence;
    private String searchWord;
    private String previousSearchWord;
}
