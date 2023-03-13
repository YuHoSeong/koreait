package com.koreait.board.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    //^ bcrypt 암호화
    public String bcrypt(String text){

        String bcryptedText = null;

        try {

            bcryptedText = passwordEncoder.encode(text);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return bcryptedText;
    }

    //^ bcrypt 복호화
    public boolean decrypt(String text){

        boolean isMatch = false;

        try {
            isMatch = passwordEncoder.matches("password", text);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isMatch;
    }
}
