package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.board.common.constant.ApiMappingPattern;
import com.koreait.board.provider.TokenProvider;
import com.koreait.board.service.AuthService;

//# auth 같은 경우는 서버를 따로 빼서 처리하는 경우가 있음.
//# DB접근을 막아두는 등 경우도 있음.

@RestController
@RequestMapping(ApiMappingPattern.AUTH)
public class AuthController {
    
    private static final String GET_TOKEN="/getToken";
    private static final String VALIDATE_TOKEN="/valid";
    private static final String BCRYPT="/bcrypt";
    private static final String DECRYPT="/decrypt";

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthService authService;

    //^ 토근생성
    @PostMapping(GET_TOKEN)
    //? POST http://localhost:4040/auth/getToken
    public String getToken(){
        return tokenProvider.create();
    }

    //^ 토큰인증
    @PostMapping(VALIDATE_TOKEN)
    public String validateToken(@RequestBody String requestBody){
        return tokenProvider.validate(requestBody);
    }

    //^ bcrypt 암호화
    @PostMapping(BCRYPT)
    public String bcrypt(@RequestBody String requestBody){
        return authService.bcrypt(requestBody);
    }

    //^ bcrypt 복호화
    @PostMapping(DECRYPT)
    public boolean decrypt(@RequestBody String requestBody){
        return authService.decrypt(requestBody);
    }
}
