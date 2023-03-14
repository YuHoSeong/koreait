package com.spring.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.spring.spring.entity.UserEntity;

// @Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{
    
    public boolean existsByEmail(String email);
    public boolean existsByTelNumber(String telNumber);
}
