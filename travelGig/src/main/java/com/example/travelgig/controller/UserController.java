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

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
                        HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView mav = new ModelAndView("login");
        System.out.println("come gere");

        String message = "";
        if (error != null) {
            message = "Invalid Credentials";
        }
        if (logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(auth);
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
            }
            message = "Logout";
            return mav;
        }
        mav.addObject("Message", message);
        return mav;

    }

    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.GET)
    public User getCurrentUser(Principal principal){

        User currentUser = userService.findByUserName(principal.getName());
        System.out.println(currentUser.getUserName());

        return currentUser;
    }

    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(@ModelAttribute User user){
        ModelAndView mav = new ModelAndView("signup");
        //when user register from register page
        //basically role is set to customer by default
        //admin will have access to change role later
        //increase userid by 1

        List<User> users = userService.findAll();

        Role defaultRole = roleService.findById(Long.valueOf(2));
        user.setRoles(Set.of(defaultRole));
        user.setUserId(Long.valueOf(users.size()+1));
        userService.save(user);
        mav.setViewName("redirect:signup");
        return mav;
    }


    @RequestMapping(value = "/role")
    public ModelAndView role(Role role) {
        ModelAndView mav = new ModelAndView("role");
        return mav;
    }

    @RequestMapping(value = "/saveRole")
    public ModelAndView saveRole(@ModelAttribute Role role) {
        ModelAndView mav = new ModelAndView("role");

        roleService.save(role);
        return mav;
    }

    @RequestMapping(value = "/signup")
    public ModelAndView signup(User user) {
        ModelAndView mav = new ModelAndView("signup");

        mav.addObject("roles",roleService.findAll());
        return mav;
    }

}
