package com.koreait.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//^ import org.springframework.stereotype.Repository;

import com.koreait.board.entity.EmployeeEntity;

//^ @Repository
//^ org.springframework.data.repository.JpaRepository를 상속한 interface 클래스는 @Repository Annotation을 생략해도 Spring 로딩시 자동으로 Repository로 등록 한다.
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer>{  
    public boolean existsByTelNumber(String telNumber);

    public EmployeeEntity findByEmployeeNumber(int employeeNumber);

    public boolean existsByDepartment(String department);
}
