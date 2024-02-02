package com.example.travelgig.controller;

import com.example.travelgig.domain.Role;
import com.example.travelgig.domain.User;
import com.example.travelgig.service.RoleService;
import com.example.travelgig.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Set;

//A controller is responsible for handling HTTP request and returning HTTP response to client.
//Rest controller is support for REST API development. In case of REST API, you will probably like to return JSON or XML as your client is no more human but machine.
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser(Principal principal){

        User currentUser = userService.findByUserName(principal.getName());
        System.out.println(currentUser.getUserName());

        return currentUser;
    }
    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(User user){
        ModelAndView mav = new ModelAndView("redirect:user");
        userService.save(user);
        List<User> userList = userService.findAll();
        mav.addObject("users", userList);
        return mav;
    }

    @RequestMapping(value = "/saveUserFromRegister")
    public ModelAndView saveUserFromRegister(@ModelAttribute User user){
        ModelAndView mav = new ModelAndView("signup");
        //when user register from register page
        //basically role is set to customer by default
        //admin will have access to change role later
        //increase userid by 1
        userService.saveFromRegister(user);
        mav.setViewName("redirect:login");
        return mav;
    }

    @RequestMapping(value = "/user")
    public ModelAndView user(User user){
        ModelAndView mav = new ModelAndView("user");
        List<User> userList = userService.findAll();
        List<Role> roleList = roleService.findAll();

        mav.addObject("roles",roleList);
        mav.addObject("users", userList);
        return mav;
    }

    @RequestMapping(value = "/signup")
    public ModelAndView signup(User user) {
        ModelAndView mav = new ModelAndView("signup");

        return mav;
    }

}
