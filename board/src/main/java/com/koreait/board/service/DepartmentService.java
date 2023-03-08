package com.koreait.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.common.constant.ResponseMessage;
import com.koreait.board.dto.request.department.GetAllDepartmentListResponseDto;
import com.koreait.board.dto.request.department.PostDepartmentRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.department.PostDepartmentResponseDto;
import com.koreait.board.entity.DepartmentEntity;
import com.koreait.board.repository.DepartmentRepository;
import com.koreait.board.repository.EmployeeRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseDto<PostDepartmentResponseDto> postDepartment(PostDepartmentRequestDto dto){

        PostDepartmentResponseDto data =null;
        int cheifEmployeeNumber = dto.getChief();
        String departmentCode = dto.getDepartmentCode();

        try {

            boolean hasDepartment = departmentRepository.existsById(departmentCode);
            if(!hasDepartment) return ResponseDto.setFail(ResponseMessage.EXIST_DEPARTMENT_CODE);

            boolean hasEmployee = employeeRepository.existsById(cheifEmployeeNumber);
            if(!hasEmployee) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_EMPLOYEE_NUMBER);

            DepartmentEntity departmentEntity = new DepartmentEntity(dto);
            departmentRepository.save(departmentEntity);

            data = new PostDepartmentResponseDto(departmentEntity);

            
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        ResponseDto<PostDepartmentResponseDto> response = ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        
        return response;
    }

    public ResponseDto<List<GetAllDepartmentListResponseDto>> getAllDepartmentList(){
        List<GetAllDepartmentListResponseDto> data = new ArrayList<GetAllDepartmentListResponseDto>();

        try {
            
            List<DepartmentEntity> departmentList = departmentRepository.findAll();
            data = GetAllDepartmentListResponseDto.copyList(departmentList);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
