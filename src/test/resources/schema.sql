drop table if exists member CASCADE;

create table member
(
    member_id bigint generated by default as identity,
    login_id varchar(30),
    member_password varchar(40),
    nick_name varchar(30),
    member_name varchar(20),
    email varchar(40),
    join_date date,
    black_list varchar(2),
    member_point int,
    primary key (member_id)
);