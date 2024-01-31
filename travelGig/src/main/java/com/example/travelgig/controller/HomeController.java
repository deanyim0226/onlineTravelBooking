package com.example.travelgig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView home(Principal principal){
        ModelAndView mav = new ModelAndView("home");
        if(principal == null){
            mav.addObject("isLoggin",true);
        }else{
            mav.addObject("isLoggin",false);
        }


        return mav;
    }

}
