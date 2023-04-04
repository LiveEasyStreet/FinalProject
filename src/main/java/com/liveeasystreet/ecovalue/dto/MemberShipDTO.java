package com.liveeasystreet.ecovalue.dto;

import lombok.Data;

@Data
public class MemberShipDTO {
    //  membership.html에 있는 input 태그의 name과 동일한 필드 변수명입니다.
    //  나중에 컨트롤러에서 @ModelAttribute -> 참고로 이름이 똑같으니까 생략 가능

    private String userId;
    private String userPassword;
    private String userCheckPassword;
    private String nickName;
    private String userName;
    private String userEmail;
    private int userAge;
}
