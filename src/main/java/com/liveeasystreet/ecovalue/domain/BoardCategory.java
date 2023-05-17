package com.liveeasystreet.ecovalue.domain;


import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BoardCategory {
    //form
    RECYCLE(1L,"리사이클"),UP_CYCLE(2L,"업사이클"),ZERO_WASTE(3L,"제로웨이스트");

    private final Long dataValue;
    private final String description;

    BoardCategory(Long dataValue,String description) {
        this.dataValue = dataValue;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public Long getDataValue(){
        return dataValue;
    }

    public static BoardCategory findByDataValue(Long value){
        return Arrays.stream(BoardCategory.values())
                .filter(e -> e.dataValue.equals(value)).findFirst().orElse(null);
    }
}
