drop table if exists member CASCADE;

create table member
(
    member_id       bigint generated by default as identity,
    login_id        varchar(30) unique,
    member_password varchar(40),
    nick_name       varchar(30) unique,
    member_name     varchar(20),
    email           varchar(40) unique,
    join_date       date,
    black_list      varchar(2),
    member_point    int,
    primary key (member_id)
);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test001', 'test001', 'test001', 'test001', 'test001@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test002', 'test002', 'test002', 'test002', 'test002@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test003', 'test003', 'test003', 'test003', 'test003@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test004', 'test004', 'test004', 'test004', 'test004@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test005', 'test005', 'test005', 'test005', 'test005@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test006', 'test006', 'test006', 'test006', 'test006@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test007', 'test007', 'test007', 'test007', 'test007@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test008', 'test008', 'test008', 'test008', 'test008@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test009', 'test009', 'test009', 'test009', 'test009@naver.com', now(), 'N', 100);

insert into member
(login_id, member_password, nick_name, member_name, email, join_date, black_list, member_point)
VALUES ('test010', 'test010', 'test010', 'test010', 'test010@naver.com', now(), 'N', 100);
