package com.example.travelgig.service;

import com.example.travelgig.domain.User;
import com.example.travelgig.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        String encryptedPassword = encoder.encode(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        return userRepository.save(user);

    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
