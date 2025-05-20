create database student_management_n0325c1;
use student_management_n0325c1;
create table student (
                         id int primary key auto_increment,
                         name VARCHAR(50),
                         score DOUBLE
);

insert into student(name, score) values ('Thịnh',9.6),
                                        ('Điêp',9.7),
                                        ('Bảo',9.8);

select id, name, score from student;