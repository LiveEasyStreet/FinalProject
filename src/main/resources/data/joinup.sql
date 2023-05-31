drop table if exists joinup CASCADE;

create table joinup (

    challenge_id    bigint,
    member_id       bigint,
    date_time        timestamp,
    is_deleted      Boolean,

    primary key (challenge_id, member_id),
    foreign key (challenge_id) references zerowastechallenge(challenge_id) on update cascade,
    foreign key (member_id) references member(member_id) on update cascade
);

insert into joinup
(challenge_id, member_id, date_time, is_deleted)
values (1, 2, now(), false);

insert into joinup
(challenge_id, member_id, date_time, is_deleted)
values (1, 2, now(), false);

insert into joinup
(challenge_id, member_id, date_time, is_deleted)
values (1, 3, now(), false);

insert into joinup
(challenge_id, member_id, date_time, is_deleted)
values (2, 1, now(), false);