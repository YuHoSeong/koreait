package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.request.department.PostDepartmentRequestDto;
import com.koreait.board.dto.response.department.DeleteDepartmentListResponseDto;
import com.koreait.board.dto.response.department.GetAllDepartmentListResponseDto;
import com.koreait.board.dto.response.department.PostDepartmentResponseDto;
import com.koreait.board.service.DepartmentService;

import org.springframework.web.bind.annotation.RequestBody;
import com.koreait.board.common.constant.ApiMappingPattern;

import java.util.List;



@RestController
@RequestMapping(ApiMappingPattern.DEPARTMENT)
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final String POST_DEPARTMENT = "/";
    private static final String GET_ALL_DEPARTMENT_LIST = "/all";
    private static final String DELETE_DEPARTMENT = "/{departmentCode}";

    //^ 부서등록
    @PostMapping(POST_DEPARTMENT)
    //? POST http://localhost:4040/apis/department/
    public ResponseDto<PostDepartmentResponseDto> postDepartment(@Valid @RequestBody PostDepartmentRequestDto requestBody) {
        ResponseDto<PostDepartmentResponseDto> response = departmentService.postDepartment(requestBody);
        return response;
    }

    //^ 부서전체조회
    @GetMapping(GET_ALL_DEPARTMENT_LIST)
    //? GET http://localhost:4040/apis/department/all
    public ResponseDto<List<GetAllDepartmentListResponseDto>> getAllDepartmentList(){
        ResponseDto<List<GetAllDepartmentListResponseDto>> response = departmentService.getAllDepartmentList();
        return response;
    }

    //^ 특정부서삭제
    @DeleteMapping(DELETE_DEPARTMENT)
    public ResponseDto<List<DeleteDepartmentListResponseDto>> deleteDepartment(@PathVariable("departmentCode") String departmentCode){
        ResponseDto<List<DeleteDepartmentListResponseDto>> response = departmentService.deleteDepartment(departmentCode);
        return response;
    }
    
}
