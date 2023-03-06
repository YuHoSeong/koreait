CREATE DATABASE EXAMPLE_DB;
USE EXAMPLE_DB;

drop table example;
drop table hibernate_sequence;

CREATE TABLE Example (
	prime INT PRIMARY KEY auto_increment,
	comment TEXT,
    number INT
);

SELECT * FROM example;
