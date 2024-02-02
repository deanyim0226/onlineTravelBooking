package com.example.travelgig.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping({"/home", "/"})
    public ModelAndView home(Principal principal){
        ModelAndView mav = new ModelAndView("home");
        if(principal == null){
            mav.addObject("isLoggin",false);
        }else{
            mav.addObject("isLoggin",true);
        }


        return mav;
    }

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

}
