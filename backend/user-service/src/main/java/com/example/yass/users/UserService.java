package com.example.yass.users;

import com.example.yass.users.dto.UserCreateOrUpdateDto;
import com.example.yass.users.model.User;
import com.example.yass.users.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public void deleteUser(UUID id) throws Exception {

        if(userRepository.existsById(id))
            throw new IllegalArgumentException(String.format("User with id: %s not found !", id));
    }

    public void createAllUsers(Set<UserCreateOrUpdateDto> users) {

        for(UserCreateOrUpdateDto userDto: users) {
            User user = new User();

            user.setRole(userDto.getRole());
            user.setName(userDto.getName());
            user.setMilitaryRank(userDto.getRank());

            userRepository.save(user);
        }
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
