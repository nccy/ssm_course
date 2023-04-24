use ssm_course;
create table s_school
(
    id           int auto_increment,
    school_name  char(20) not null,
    constraint s_school_pk
        primary key (id)
);
create table c_course
(
    id      int auto_increment,
    name    char(20) not null,
    hours   int      not null,
    schools int      null,
    constraint c_pk
        primary key (id),
    constraint s_c
        foreign key (schools) references s_school (id)
            on update cascade on delete set null
);
delete from c_course;
delete from s_school;
alter table c_course AUTO_INCREMENT 1;
alter table s_school AUTO_INCREMENT 1;
insert into s_school values(null,'计算机学院');
insert into s_school values(null,'外国语学院');
insert into c_course values(null,'C语⾔程序设计',70,1);
insert into c_course values(null,'Python程序设计',70,1);
insert into c_course values(null,'⼤学英语',96,2);
insert into c_course values(null,'⾼级Web技术',32,1);

#删除image列#
ALTER TABLE c_course
DROP COLUMN image;
#添加image列#
ALTER TABLE c_course ADD image varchar(255);
create table user
(
    email    char(30) not null,
    password char(30) not null,
    constraint user_pk
        primary key (email)
);
insert into user values('nccy_lin@qq.com','123456');
insert into user values('123@qq.com','123');