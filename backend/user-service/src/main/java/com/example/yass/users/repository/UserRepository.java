package com.example.yass.users.repository;

import com.example.yass.users.model.enums.MilitaryRank;
import com.example.yass.users.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    void deleteUserByNameAndMilitaryRank(String name, MilitaryRank rank);
}
