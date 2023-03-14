package com.spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.spring.spring.entity.LikyEntity;
import com.spring.spring.entity.primaryKey.LikyPK;

// @Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPK>{
    
}
