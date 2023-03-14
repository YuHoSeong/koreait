package com.spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.spring.spring.entity.CommentEntity;

// @Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    
}
