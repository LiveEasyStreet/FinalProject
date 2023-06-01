drop table if exists image CASCADE;

drop sequence image_seq;
CREATE SEQUENCE image_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE image (
                       board_id          bigint,
                       image_id          bigint,
                       upload_file_name  varchar(100),
                       store_file_name   varchar(100),
                       upload_date       timestamp,
                       primary key (image_id)
);

-- 테스트 데이터는 오로지 테스트용, 테스트 완료후 지울것

insert into image
(board_id, image_id, upload_file_name, store_file_name, upload_date)
VALUES (1, 1, 'upload1','store1', now());

insert into image
(board_id, image_id, upload_file_name, store_file_name, upload_date)
VALUES (1, 2, 'upload2','store2', now());