package com.example.travelgig.service;


import com.example.travelgig.domain.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User save(User user);
    public void deleteUserById(long userId);
    public User findByUserId(long userId);
    public User findByUserName(String userName);

    public User saveFromRegister(User user);

    public boolean isAmdin(User user);
}
