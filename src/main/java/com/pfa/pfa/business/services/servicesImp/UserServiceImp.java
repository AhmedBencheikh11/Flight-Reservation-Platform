package com.pfa.pfa.business.services.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.pfa.business.services.UserService;
import com.pfa.pfa.dao.entities.User;
import com.pfa.pfa.dao.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByMail(email);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByMail(email);
    }

    @Override
    public boolean isValidUser(String email, String password) {
        return userRepository
                .findByMail(email)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }

   @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
