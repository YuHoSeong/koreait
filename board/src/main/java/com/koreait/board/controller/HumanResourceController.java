package com.koreait.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.dto.request.humanResource.PatchHumanResourceRequestDto;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;
import com.koreait.board.dto.response.ResponseDto;
import com.koreait.board.dto.response.humanResource.GetHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PatchHumanResourceResponseDto;
import com.koreait.board.dto.response.humanResource.PostHumanResourceResponseDto;
import com.koreait.board.service.HumanResourceService;
import static com.koreait.board.common.constant.ApiMappingPattern.*;

@RestController
@RequestMapping(HR)
public class HumanResourceController {

    @Autowired
    private HumanResourceService humanResourceService;

    private static final String POST_HUMAN_RESOURCE = "/";
    private static final String GET_HUMAN_RESOURCE = "/{employeeNumber}";
    private static final String PATCH_HUMAN_RESOURCE = "/";

    //? 사원등록
    @PostMapping(POST_HUMAN_RESOURCE)
    public ResponseDto<PostHumanResourceResponseDto> postHumanResource(@Valid @RequestBody PostHumanResourceRequestDto requestbody){

        ResponseDto<PostHumanResourceResponseDto> response = humanResourceService.postHumanResource(requestbody);
        
        return response;
    }

    //? 사원조회
    @GetMapping(GET_HUMAN_RESOURCE)
    public ResponseDto<GetHumanResourceResponseDto> GetHumanResource(@PathVariable("employeeNumber") int employeeNumber){
        
        ResponseDto<GetHumanResourceResponseDto> response = humanResourceService.getHumanResource(employeeNumber);
        
        return response;
    }

    //? 사원정보수정
    @PatchMapping(PATCH_HUMAN_RESOURCE)
    public ResponseDto<PatchHumanResourceResponseDto> patchHumanResource(@Valid @RequestBody PatchHumanResourceRequestDto requestBody){
        ResponseDto<PatchHumanResourceResponseDto> response = humanResourceService.patchHumanResource(requestBody);
        return response;
    }
    
}
