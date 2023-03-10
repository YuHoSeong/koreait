package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.common.constant.ApiMappingPattern;
import com.koreait.board.provider.TokenProvider;

//# auth 같은 경우는 서버를 따로 빼서 처리하는 경우가 있음.
//# DB접근을 막아두는 등 경우도 있음.

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
public class AuthController {
    
    private static final String GET_TOKEN="/getToken";
    private static final String VALIDATE_TOKEN="/valid";

    @Autowired
    private TokenProvider tokenProvider;

    //^ 토근생성
    @PostMapping(GET_TOKEN)
    //? POST http://localhost:4040/auth/getToken
    public String getToken(){
        return tokenProvider.create();
    }

    //^ 토큰인증
    @PostMapping(VALIDATE_TOKEN)
    public String validateToken(@RequestBody String requestbody){
        return tokenProvider.validate(requestbody);
    }
}
