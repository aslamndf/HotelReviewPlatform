package com.aslam.userService.services;

import com.aslam.userService.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(UUID userId);

    User updateUser(User user, UUID userId);

    void deleteUser(UUID userId);
}
