package com.spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.spring.spring.entity.SearchWordLogEntity;

// @Repository
public interface SearchWordLogRepository extends JpaRepository<SearchWordLogEntity, Integer>{
    
}
