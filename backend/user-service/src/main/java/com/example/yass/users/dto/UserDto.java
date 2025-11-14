package com.example.yass.users.dto;

import com.example.yass.users.model.MilitaryRank;
import com.example.yass.users.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private UUID id;
    private String name;
    private MilitaryRank rank;
    private UserRole role;

}
