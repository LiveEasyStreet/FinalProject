drop table if exists board CASCADE;
CREATE TABLE board (
       board_id          bigint generated by default as identity,
       title             varchar(100),
       board_category    bigint,
       tag               bigint,
       contents          text,
       member_id         bigint,
       upload_date       timestamp,
       edit_date         timestamp,
       delete_date       timestamp,
       views             int,
       primary key (board_id)
);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 1', 1, 0, '이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 2', 1, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 3', 1, 0, '이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 4', 1, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);
insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 5', 1, 0, '이것은 테스트 데이터 입니다.', 3,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 6', 1, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 7', 1, 0,'이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 8', 1, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 9', 1, 0,'이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 10', 1, 0,'이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 11', 1, 0,'이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 12', 1, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 13', 1, 0, '이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 14', 1, 0,'이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 1', 1, 0, '이것은 테스트 데이터 입니다.1', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 2', 1, 0, '이것은 테스트 데이터 입니다.2', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 3', 1, 0, '이것은 테스트 데이터 입니다.3', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 4', 1, 0,'이것은 테스트 데이터 입니다.4', 2,now(), 0);
insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 5', 1, 0,'이것은 테스트 데이터 입니다.5', 3,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 6', 1, 0,'이것은 테스트 데이터 입니다.6', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 7', 1, 0,'이것은 테스트 데이터 입니다.7', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 8', 1, 0,'이것은 테스트 데이터 입니다.8', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 9', 1, 0,'이것은 테스트 데이터 입니다.9', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 10', 1, 0,'이것은 테스트 데이터 입니다.10', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 11', 1, 0,'이것은 테스트 데이터 입니다.11', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 12', 1, 0, '이것은 테스트 데이터 입니다.12', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 13', 1, 0, '이것은 테스트 데이터 입니다.13', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 14', 1, 0, '이것은 테스트 데이터 입니다.14', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 1', 2, 0, '이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 2', 2, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 3', 2, 0, '이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 4', 2, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);
insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 5', 2, 0, '이것은 테스트 데이터 입니다.', 3,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 6', 2, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 7', 2, 0,'이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 8', 2, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 9', 2, 0,'이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 10', 2, 0,'이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 11', 2, 0,'이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 12', 2, 0, '이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 13', 2, 0, '이것은 테스트 데이터 입니다.', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 14', 2, 0,'이것은 테스트 데이터 입니다.', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 1', 2, 0, '이것은 테스트 데이터 입니다.1', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 2', 2, 0, '이것은 테스트 데이터 입니다.2', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 3', 2, 0, '이것은 테스트 데이터 입니다.3', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 4', 2, 0,'이것은 테스트 데이터 입니다.4', 2,now(), 0);
insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 5', 2, 0,'이것은 테스트 데이터 입니다.5', 3,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 6', 2, 0,'이것은 테스트 데이터 입니다.6', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 7', 2, 0,'이것은 테스트 데이터 입니다.7', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 8', 2, 0,'이것은 테스트 데이터 입니다.8', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 9', 2, 0,'이것은 테스트 데이터 입니다.9', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 10', 2, 0,'이것은 테스트 데이터 입니다.10', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 11', 2, 0,'이것은 테스트 데이터 입니다.11', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 12', 2, 0, '이것은 테스트 데이터 입니다.12', 2,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 13', 2, 0, '이것은 테스트 데이터 입니다.13', 1,now(), 0);

insert into board
(title, board_category, tag, contents, member_id, upload_date, views)
VALUES ('테스트 제목 14', 2, 0, '이것은 테스트 데이터 입니다.14', 2,now(), 0);