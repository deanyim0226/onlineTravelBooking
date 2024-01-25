package com.example.travelgig.service;


import com.example.travelgig.domain.Role;
import com.example.travelgig.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User retrievedUser = userService.findByUserName(username);

        if(retrievedUser == null){
            //error user does not exist
            throw new UsernameNotFoundException(username);
        }

        Set<Role> roles = retrievedUser.getRoles();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(Role role : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(retrievedUser.getUserName(),retrievedUser.getUserPassword(), grantedAuthorities);
    }
}
