package com.example.yass.users.service;

import com.example.yass.users.model.dto.UserDto;
import com.example.yass.users.model.entity.User;
import com.example.yass.users.model.enums.MilitaryRank;
import com.example.yass.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        user.setMilitaryRank(userDto.getRank());

        return userRepository.save(user);
    }

    public void deleteUserByNameAndRank(String name, MilitaryRank rank) throws Exception {
        userRepository.deleteUserByNameAndMilitaryRank(name, rank);
    }

    public Set<User> createAllUsers(Set<UserDto> users) {

        final Set<User> usersToSave = new HashSet<>();

        for(UserDto userDto: users) {
            User user = new User();

            user.setRole(userDto.getRole());
            user.setName(userDto.getName());
            user.setMilitaryRank(userDto.getRank());
            user.setPassword(userDto.getPassword());

            usersToSave.add(user);
        }

        return new HashSet<>(userRepository.saveAll(usersToSave));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUser(String name, MilitaryRank militaryRank) {
        userRepository.deleteUserByNameAndMilitaryRank(name, militaryRank);
    }

    public User getUser(String id) {
        return userRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("l'utilisateur n'éxiste pas."));

        return org.springframework.security.core.userdetails.User.builder().username(user.getName())
                .password(user.getPassword()).authorities(user.getAuthorities()).build();
    }
}
