package com.pfa.pfa.business.services;

import com.pfa.pfa.dao.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserByEmail(String email);
    boolean emailExists(String email);
    boolean isValidUser(String email, String password);
    List<User> getAllUsers();

}
