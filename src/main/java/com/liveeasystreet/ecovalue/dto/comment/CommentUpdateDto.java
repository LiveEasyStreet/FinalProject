package com.liveeasystreet.ecovalue.dto.comment;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentUpdateDto {

    // 작성 내용
    private String content;

}
