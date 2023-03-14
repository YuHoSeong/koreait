package com.spring.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring.common.constant.ApiMappingPattern;
import com.spring.spring.dto.request.auth.SignInRequestDto;
import com.spring.spring.dto.request.auth.SignUpRequestDto;
import com.spring.spring.dto.response.ResponseDto;
import com.spring.spring.dto.response.auth.SignInResponseDto;
import com.spring.spring.dto.response.auth.SignUpResponseDto;
import com.spring.spring.service.AuthService;

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
public class AuthController {
    
    private final String SIGN_UP = "/sign-up";
    private final String SIGN_IN = "/sign-in";

    @Autowired
    private AuthService authService;

    //^ 회원가입
    @PostMapping(SIGN_UP)
    //? http://localhost:4040/auth/sign-up
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestBody){
        ResponseDto<SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    //^ 로그인
    @PostMapping(SIGN_IN)
    //? http://localhost:4040/auth/sign-in
    //? 임시데이터(email: ghtjd026@gmail.com, password: 123456789)
    public ResponseDto<SignInResponseDto> signIn(@Valid @RequestBody SignInRequestDto requestBody){
        ResponseDto<SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

}
