create table student(
                        id int auto_increment primary key,
                        name varchar(50),
                        score double
);

insert into student (name, score)
values ('Luong', 10.0),
       ('Thien', 8.0),
       ('Truong', 9.0);

select id, name, score from student;

select id, name, score from student where id = 1;

insert into student (name, score)
values ('Long', 2.0);