drop table if exists comment CASCADE;
drop sequence comment_seq;
CREATE SEQUENCE comment_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE comment (
                       comment_id       bigint,
                       board_id         bigint,
                       member_id        bigint,
                       head_comment     bigint,
                       contents         varchar(1000),
                       upload_date      timestamp,
                       edit_date        timestamp,
                       delete_date      timestamp,

                       primary key (comment_id)
);

insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 1, 1,CURRVAL('comment_seq'),'동일인물 테스트 댓글 1', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 1, 1,CURRVAL('comment_seq'),'동일인물 테스트 댓글 2', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 1, 1, CURRVAL('comment_seq'), '동일인물 테스트 댓글 2', now());

insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 1, 2, CURRVAL('comment_seq'), '테스트 댓글 3', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 1, 2, 1, '테스트 대댓글 1', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 1, 1, 1, '본인 테스트 대댓글 1', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 47, 1,CURRVAL('comment_seq'),'동일인물 테스트 댓글 1', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 47, 1,CURRVAL('comment_seq'),'동일인물 테스트 댓글 2', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 47, 1, CURRVAL('comment_seq'), '동일인물 테스트 댓글 2', now());

insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 47, 2, CURRVAL('comment_seq'), '테스트 댓글 3', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 47, 2, 1, '테스트 대댓글 1', now());


insert into comment
(comment_id, board_id, member_id, head_comment, contents, upload_date)
VALUES (NEXTVAL('comment_seq'), 47, 1, 1, '본인 테스트 대댓글 1', now());
