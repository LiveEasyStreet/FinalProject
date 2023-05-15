package com.liveeasystreet.ecovalue.dto.manager;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDto {
    @NotBlank
    private String managerLoginId;
    @NotBlank
    private String managerPassword;
    @NotBlank
    private String secondManagerPassword;
}
