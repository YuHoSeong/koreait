package com.koreait.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.board.common.constant.ResponseMessage;
import com.koreait.board.dto.request.department.PostDepartmentRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.department.DeleteDepartmentListResponseDto;
import com.koreait.board.dto.response.department.GetAllDepartmentListResponseDto;
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

    //^ 부서등록
    public ResponseDto<PostDepartmentResponseDto> postDepartment(PostDepartmentRequestDto dto){

        PostDepartmentResponseDto data =null;
        int cheifEmployeeNumber = dto.getChief();
        String departmentCode = dto.getDepartmentCode();

        try {
            //? 해당 부서코드가 이미 존재하는지 확인
            boolean hasDepartment = departmentRepository.existsById(departmentCode);
            if(hasDepartment) return ResponseDto.setFail(ResponseMessage.EXIST_DEPARTMENT_CODE);
            //? 사원테이블에 부서장코드가 존재하는지 확인
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

    //^ 부서전체조회
    public ResponseDto<List<GetAllDepartmentListResponseDto>> getAllDepartmentList(){
        List<GetAllDepartmentListResponseDto> data = null;

        try {
            
            List<DepartmentEntity> departmentList = departmentRepository.findAll();
            data = GetAllDepartmentListResponseDto.copyList(departmentList);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    //^ 특정부서삭제
    public ResponseDto<List<DeleteDepartmentListResponseDto>> deleteDepartment(String departmentCode){
        List<DeleteDepartmentListResponseDto> data = null;

        try {
            //? 부서코드가 존재하는지 확인
            boolean hasDepartment = departmentRepository.existsById(departmentCode);
            if(!hasDepartment) return ResponseDto.setFail(ResponseMessage.NOT_EXIST_DEPARTMENT_CODE);

            //? 사원테이블에서 해당 부서코드를 참조하고 있는지 확인
            boolean hasReferenceEmployee = employeeRepository.existsByDepartment(departmentCode);
            if(hasReferenceEmployee) return ResponseDto.setFail(ResponseMessage.EXIST_REFERENCE_DEPARTMENT_CODE);

            departmentRepository.deleteById(departmentCode);

            List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
            data = DeleteDepartmentListResponseDto.copyList(departmentEntities);
            
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
    
}
