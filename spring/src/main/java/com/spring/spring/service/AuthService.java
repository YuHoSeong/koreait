package com.spring.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.spring.common.constant.ResponseMessage;
import com.spring.spring.dto.request.auth.SignUpRequestDto;
import com.spring.spring.dto.response.ResponseDto;
import com.spring.spring.dto.response.auth.SignUpResponseDto;
import com.spring.spring.entity.UserEntity;
import com.spring.spring.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public ResponseDto<SignUpResponseDto> SignUp(SignUpRequestDto dto){

        SignUpResponseDto data = null;
        String email = dto.getEmail();
        String telNumber = dto.getTelNumber();
        String password = dto.getPassword();

        try {

            boolean isEmail = userRepository.existsByEmail(email);
            boolean isTelNumber = userRepository.existsByTelNumber(telNumber);
            if(isEmail && isTelNumber) return ResponseDto.setFail(ResponseMessage.EXISTED_EMAIL_AND_TELNUMBER);
            if(isEmail) return ResponseDto.setFail(ResponseMessage.EXISTED_EMAIL);
            if(isTelNumber) return ResponseDto.setFail(ResponseMessage.EXISTED_TELNUMBER);

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            data = new SignUpResponseDto(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
