package com.liveeasystreet.ecovalue.domain;

import com.liveeasystreet.ecovalue.dto.manager.ManagerDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Manager {

    // 내부 관리용 관리자 id
    private Long managerId;

    // 로그인 시 사용되는 관리자 id
    private String managerLoginId;

    // 패스워드
    private String managerPassword;

    // 2차 비번
    private String secondManagerPassword;

    // 닉네임
    private String managerName;

//    public Manager(ManagerDto managerDto){
//        this.loginId = managerDto.getLoginId();
//        this.managerPassword = managerDto.getManagerPassword();
//    }
}
