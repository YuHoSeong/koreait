package com.spring.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.spring.common.constant.ResponseMessage;
import com.spring.spring.dto.request.auth.SignInRequestDto;
import com.spring.spring.dto.request.auth.SignUpRequestDto;
import com.spring.spring.dto.response.ResponseDto;
import com.spring.spring.dto.response.auth.SignInResponseDto;
import com.spring.spring.dto.response.auth.SignUpResponseDto;
import com.spring.spring.entity.UserEntity;
import com.spring.spring.provider.TokenProvider;
import com.spring.spring.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    //^ 회원가입
    public ResponseDto<SignUpResponseDto> signUp(SignUpRequestDto dto){

        SignUpResponseDto data = null;
        String email = dto.getEmail();
        String telNumber = dto.getTelNumber();
        String password = dto.getPassword();

        try {

            //? 이메일 중복 확인
            boolean hasEmail = userRepository.existsByEmail(email);
            if(hasEmail) return ResponseDto.setFail(ResponseMessage.EXISTED_EMAIL);
            //? 연락처 중복 확인
            boolean hasTelNumber = userRepository.existsByTelNumber(telNumber);
            if(hasTelNumber) return ResponseDto.setFail(ResponseMessage.EXISTED_TELNUMBER);
            //? 패스워드 암호화
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

    //^ 로그인
    public ResponseDto<SignInResponseDto> signIn(SignInRequestDto dto){
        SignInResponseDto data = null;
        UserEntity userEntity = null;

        String email = dto.getEmail();
        String password = dto.getPassword();

        try {

            userEntity = userRepository.findByEmail(email);
            //? 이메일이 존재하는지 검증
            if(userEntity == null){
                System.out.println("email이 틀렸습니다.");
                return ResponseDto.setFail(ResponseMessage.FAIL_SIGN_IN);
            } 

            //? 패스워드가 일치하는지 검증
            boolean IsEqualPassword = passwordEncoder.matches(password, userEntity.getPassword());
            if(!IsEqualPassword){
                System.out.println("패스워드가 틀렸습니다.");
                return ResponseDto.setFail(ResponseMessage.FAIL_SIGN_IN);
            } 

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.DATABASE_ERROR);
        }

        try {
            //? 토큰 생성
            String token = tokenProvider.create(email);
            data = new SignInResponseDto(userEntity, token);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFail(ResponseMessage.FAIL_SIGN_IN);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
