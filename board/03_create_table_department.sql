USE EXAMPLE_DB;

create table Department(
	department_code varchar(5) primary key comment '부서코드',
    name varchar(100) not null comment '부서명',
    cheif int not null comment '부서장',
    tel_number varchar(15) not null comment '부서연락처',
    
    constraint department_fk_cheif
    foreign key (cheif)
    references Employee (employee_number)
);

alter table employee modify column department varchar(5) comment '부서코드';

alter table employee
add 
constraint employee_fk_dapartment
foreign key (department) 
references department(department_code);