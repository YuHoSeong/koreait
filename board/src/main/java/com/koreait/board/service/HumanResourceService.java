package com.koreait.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.PostHumanResourceResponseDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.repository.DepartmentRepository;
import com.koreait.board.repository.EmployeeRepository;

@Service
public class HumanResourceService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(PostHumanResourceRequestDto dto){

        String telNumber = dto.getTelNumber();
        try {
            boolean hasTelNumber = employeeRepository.existsByTelNumber(telNumber);
            if(hasTelNumber) return ResponseDto.setFail("Existed TelNumber");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail("Database Error");
        }
        PostHumanResourceResponseDto data = new PostHumanResourceResponseDto();
        ResponseDto<PostHumanResourceResponseDto> result = ResponseDto.setSuccess("success", data);

        return result;
    }

}
