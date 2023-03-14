package com.spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.spring.spring.entity.BoardEntity;

// @Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer>{
    
}
