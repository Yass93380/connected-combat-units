package com.example.yass.users.model.dto;

import com.example.yass.users.model.enums.MilitaryRank;
import com.example.yass.users.model.enums.UserRole;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserCreateOrUpdateDto {

    @Nullable
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private MilitaryRank rank;

    @NotNull
    private UserRole role;
}
