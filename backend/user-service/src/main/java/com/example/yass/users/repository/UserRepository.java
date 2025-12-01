package com.example.yass.users.repository;

import com.example.yass.users.model.entity.User;
import com.example.yass.users.model.enums.MilitaryRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Transactional
    void deleteUserByNameAndMilitaryRank(String name, MilitaryRank rank);

    Optional<User> findUserByName(String name);
}
