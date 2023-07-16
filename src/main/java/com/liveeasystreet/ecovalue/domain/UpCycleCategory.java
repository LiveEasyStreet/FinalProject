package com.liveeasystreet.ecovalue.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum UpCycleCategory {
    //form
    NONE(0L,"없음",false),INFO(1L,"정보",false),ASK(2L,"질문",false);

    private final Long dataValue;
    private final String description;
    private final Boolean isEnd;

    UpCycleCategory(Long dataValue,String description,Boolean isEnd) {
        this.dataValue = dataValue;
        this.description = description;
        this.isEnd = isEnd;
    }

    public String getDescription() {
        return description;
    }
    public Long getDataValue(){
        return dataValue;
    }

    /**
     * 이부분 수정 필요할수도?
     * @param value
     * @return
     */
    public static UpCycleCategory findByDataValue(Long value){
        return Arrays.stream(UpCycleCategory.values())
                .filter(e -> e.dataValue.equals(value)).findFirst().orElse(null);
    }

    public static List<UpCycleCategory> getNonEndCategories() {
        return Arrays.stream(UpCycleCategory.values())
                .filter(category -> !category.isEnd)
                .collect(Collectors.toList());
    }

}
