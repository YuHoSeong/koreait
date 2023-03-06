package com.koreait.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostHumanResourceResponseDto {
    //? 사번
    private int employeeNumber;
    //? 직급
    private String position;
    //? 이름
    private String name;
    //? 나이
    private int age;
    //? 성별
    private String gender;
    //? 학력
    private String academicAbility;
    //? 생년월일
    private String birth;
    //? 연락처
    private String telNumber;
    //? 주소
    private String address;
    //? 상세주소
    private String addressDetail;
    //? 입사일
    private String joinDate;
    //? 퇴사일
    private String resignationDate;
    //? 부서 코드
    private String department;
    //? 연봉
    private int annualIncome;
    //? 비고
    private String note;
}
