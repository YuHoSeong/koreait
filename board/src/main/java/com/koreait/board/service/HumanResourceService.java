package com.koreait.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.dto.request.humanResource.PatchHumanResourceRequestDto;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PatchHumanResourceResponseDto;
import com.koreait.board.entity.EmployeeEntity;
import com.koreait.board.repository.DepartmentRepository;
import com.koreait.board.repository.EmployeeRepository;

import static com.koreait.board.common.constant.ResponseMessage.*;

@Service
public class HumanResourceService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    //^ 사원등록
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(PostHumanResourceRequestDto dto){

        PostHumanResourceResponseDto data = null;

        String telNumber = dto.getTelNumber();
        String departmentCode = dto.getDepartment();

        try {
            //? 사원 연락처를 입력했는지 확인
            boolean hasTelNumber = employeeRepository.existsByTelNumber(telNumber);
            if(hasTelNumber) return ResponseDto.setFail(EXIST_PHONE_NUMBER);

            if(departmentCode != null) {
                boolean hasDepartment = departmentRepository.existsById(departmentCode);
                if(!hasDepartment) return ResponseDto.setFail(NOT_EXIST_DEPARTMENT_CODE);
            }

            EmployeeEntity employeeEntity = new EmployeeEntity(dto);
            employeeRepository.save(employeeEntity);

            data = new PostHumanResourceResponseDto(employeeEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(DATABASE_ERROR);
        }
        
        ResponseDto<PostHumanResourceResponseDto> result = ResponseDto.setSuccess(SUCCESS, data);

        return result;
    }

    //^ 사원조회
    public ResponseDto<GetHumanResourceResponseDto> getHumanResource(int employeeNumber){

        GetHumanResourceResponseDto data = null;
        
        try{
            // boolean hasEmployee = employeeRepository.existsById(employeeNumber);
            // if(!hasEmployee) return ResponseDto.setFail(NOT_EXIST_EMPLOYEE_NUMBER);
            // EmployeeEntity employeeEntity = employeeRepository.findById(employeeNumber).get();
            //? 사원코드가 존재하는지 확인
            EmployeeEntity employeeEntity = employeeRepository.findByEmployeeNumber(employeeNumber);
            if(employeeEntity == null) return ResponseDto.setFail(NOT_EXIST_EMPLOYEE_NUMBER);

            data = new GetHumanResourceResponseDto(employeeEntity);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseDto.setFail(DATABASE_ERROR);
        }
        ResponseDto<GetHumanResourceResponseDto> result = ResponseDto.setSuccess(SUCCESS, data);
        return result;
    }

    //^ 사원정보수정
    public ResponseDto<PatchHumanResourceResponseDto> patchHumanResource(PatchHumanResourceRequestDto dto){
        PatchHumanResourceResponseDto data = null;

        int employeeNumber = dto.getEmployeeNumber();
        String departmentCode = dto.getDepartment();

        try {
            //? 사원코드가 존재하는지 확인
            boolean hasEmployee = employeeRepository.existsById(employeeNumber);
            if(!hasEmployee) return ResponseDto.setFail(NOT_EXIST_EMPLOYEE_NUMBER);
            //? 부서코드를 입력했을때
            if(departmentCode != null){
                //? 부서코드가 존재하는지 확인
                boolean hasDepartment = departmentRepository.existsById(departmentCode);
                if(!hasDepartment) return ResponseDto.setFail(NOT_EXIST_DEPARTMENT_CODE);
            }
            
            EmployeeEntity updatedEmployeeEntity = new EmployeeEntity(dto);
            employeeRepository.save(updatedEmployeeEntity);

            data = new PatchHumanResourceResponseDto(updatedEmployeeEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(DATABASE_ERROR);
        }

        ResponseDto<PatchHumanResourceResponseDto> result = ResponseDto.setSuccess(SUCCESS, data);
        return result;
    }

}
