drop table if exists thumbup CASCADE;

create table thumbup
(
    board_id         bigint,
    member_id        bigint,
    dateTime         timestamp,
    isDeleted        Boolean,

    primary key (board_id, member_id),
    FOREIGN KEY (board_id) REFERENCES board(board_id) on update cascade,
    FOREIGN KEY (member_id) REFERENCES member(member_id) on update cascade
);

insert into thumbup
(board_id, member_id, dateTime, isDeleted)
VALUES (1, 1, now(), false);

insert into thumbup
(board_id, member_id, dateTime, isDeleted)
VALUES (1, 2, now(), false);

insert into thumbup
(board_id, member_id, dateTime, isDeleted)
VALUES (1, 3, now(), false);

insert into thumbup
(board_id, member_id, dateTime, isDeleted)
VALUES (2, 1, now(), false);
