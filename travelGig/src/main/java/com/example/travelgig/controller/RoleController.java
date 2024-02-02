package com.example.travelgig.controller;


import com.example.travelgig.domain.Role;
import com.example.travelgig.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/role")
    public ModelAndView role(Role role) {
        ModelAndView mav = new ModelAndView("role");
        List<Role> roleList = roleService.findAll();

        mav.addObject("roles",roleList);
        return mav;
    }

    @RequestMapping(value = "/saveRole")
    public ModelAndView saveRole(@ModelAttribute Role role) {
        ModelAndView mav = new ModelAndView("redirect:role");

        roleService.save(role);
        return mav;
    }
}
