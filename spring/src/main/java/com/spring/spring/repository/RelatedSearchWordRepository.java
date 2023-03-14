package com.spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.spring.spring.entity.RelatedSearchWordEntity;

// @Repository
public interface RelatedSearchWordRepository extends JpaRepository<RelatedSearchWordEntity, Integer>{
    
}
