package com.example.yass.users.dto;

import com.example.yass.users.model.MilitaryRank;
import com.example.yass.users.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateOrUpdateDto {

    @NotBlank
    private String name;

    @NotNull
    private MilitaryRank rank;

    @NotNull
    private UserRole role;
}
