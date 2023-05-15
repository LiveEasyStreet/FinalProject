package com.liveeasystreet.ecovalue.dto.manager;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerLoginDto {
    @NotBlank
    private String managerLoginId;
    @NotBlank
    private String managerPassword;
    @NotBlank
    private String secondManagerPassword;
}
