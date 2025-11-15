package com.example.yass.users.service;

import com.example.yass.users.model.dto.UserCreateOrUpdateDto;
import com.example.yass.users.model.enums.MilitaryRank;
import com.example.yass.users.model.entity.User;
import com.example.yass.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateOrUpdateDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        user.setMilitaryRank(userDto.getRank());

        return userRepository.save(user);
    }

    public void deleteUserByNameAndRank(String name, MilitaryRank rank) throws Exception {
        userRepository.deleteUserByNameAndMilitaryRank(name, rank);
    }

    public Set<User> createAllUsers(Set<UserCreateOrUpdateDto> users) {

        final Set<User> usersToSave = new HashSet<>();

        for(UserCreateOrUpdateDto userDto: users) {
            User user = new User();

            user.setRole(userDto.getRole());
            user.setName(userDto.getName());
            user.setMilitaryRank(userDto.getRank());

            usersToSave.add(user);
        }

        return new HashSet<>(userRepository.saveAll(usersToSave));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }
}
