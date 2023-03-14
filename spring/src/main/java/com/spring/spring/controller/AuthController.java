package com.spring.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring.common.constant.ApiMappingPattern;
import com.spring.spring.dto.request.auth.SignUpRequestDto;
import com.spring.spring.dto.response.ResponseDto;
import com.spring.spring.dto.response.auth.SignUpResponseDto;
import com.spring.spring.service.AuthService;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
public class AuthController {
    
    private final String SIGN_UP = "/sign-up";

    @Autowired
    private AuthService authService;

    @PostMapping(SIGN_UP)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestBody){
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

}
