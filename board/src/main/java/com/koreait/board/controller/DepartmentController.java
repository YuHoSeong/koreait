package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.department.PostDepartmentResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import static com.koreait.board.common.constant.ApiMappingPattern.*;



@RestController
@RequestMapping(DEPARTMENT)
public class DepartmentController {

    private static final String POST_DEPARTMENT = "/";

    //? 부서등록
    @PostMapping(POST_DEPARTMENT)
    public ResponseDto<PostDepartmentResponseDto> postDepartment(@Valid @RequestBody PostDepartmentResponseDto requestBody) {
        return null;
    }
    
    
}
