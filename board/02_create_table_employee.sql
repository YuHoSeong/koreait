USE EXAMPLE_DB;

CREATE TABLE Employee (
	employee_number int auto_increment primary key comment '사번',
    position varchar(10) not null comment '직급',
    name varchar(20) not null comment '이름',
    age int not null comment '나이',
    gender varchar(10) not null comment '성별',
    academic_ability varchar(20) not null comment '학력',
    birth date not null comment '생년월일',
    tel_number varchar(15) not null comment '연락처',
    address text not null comment '주소',
    address_detail text not null comment '상세주소',
    join_date date not null comment '입사일',
    resignation_date date comment '퇴사일',
    department varchar(45) default '미정' comment '부서',
    annual_income int not null comment '연봉',
    note text comment '비고'
);
