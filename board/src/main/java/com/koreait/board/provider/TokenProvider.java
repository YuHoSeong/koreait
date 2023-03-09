package com.koreait.board.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {

    @Value("${jwt.securekey}")
    private String SECURE_KEY;
    
    public String create(){
        Date expireDate = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));

        //# JWT 참고자료 "JWT Decoding 예" https://prickle-textbook-12d.notion.site/1-10-Security-e15e7ca5086a451ea7e19bad3e654f88
        String jwt = 
            //? Jwts 클래스를 이용해서 JWT빌드(생성)
            Jwts.builder()
            //? JWT_HEADER(암호화 알고리즘, 암호화 키)
            .signWith(SignatureAlgorithm.HS256, SECURE_KEY)
            //? JWT_PAYLOAD(sub값)
            .setSubject("id")
            //? JWT_PAYLOAD(iat값_생성시간)
            .setIssuedAt(new Date())
            //? JWT_PAYLOAD(exp값_만료시간)
            .setExpiration(expireDate)
            //? 암호화 알고리즘과 암호화 키를 이용해서 지정한 값들을 토큰으로 변형
            .compact();

        return jwt;

    }
}
