package com.liveeasystreet.ecovalue.dto.board;


import com.liveeasystreet.ecovalue.domain.BoardCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class BoardUpdateDto {

    // 제목
    private String title;

    // 내용
    private String contents;

}
