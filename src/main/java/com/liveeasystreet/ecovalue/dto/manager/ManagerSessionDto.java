package com.liveeasystreet.ecovalue.dto.manager;


import com.liveeasystreet.ecovalue.domain.Manager;
import com.liveeasystreet.ecovalue.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ManagerSessionDto {

    private String loginId;
    private String managerName;

    public ManagerSessionDto() {

    }

    public ManagerSessionDto(Manager manager) {
        this.loginId = manager.getManagerLoginId();
        this.managerName = manager.getManagerName();
    }
}
