package com.example.travelgig.service;

import com.example.travelgig.domain.Role;
import com.example.travelgig.domain.User;
import com.example.travelgig.repository.RoleRepository;
import com.example.travelgig.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


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

    @Override
    public User saveFromRegister(User user) {
        List<User> users = userRepository.findAll();
        Role defaultRole = roleRepository.findById(Long.valueOf(2)).orElse(null);
        user.setRoles(Set.of(defaultRole));
        user.setUserId(Long.valueOf(users.size()+1));
        String encryptedPassword = encoder.encode(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public boolean isAmdin(User user) {
        Role adminRole = roleRepository.findById(Long.valueOf(1)).orElse(null);

        if(user.getRoles().contains(adminRole)){
            return true;
        }

        return false;
    }
}
