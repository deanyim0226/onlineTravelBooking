package com.example.bookingmicroservice.controller;


import com.example.bookingmicroservice.domain.Guest;
import com.example.bookingmicroservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GuestController {

    @Autowired
    GuestService guestService;

    @RequestMapping(value = "/saveGuest", method = RequestMethod.POST)
    public Guest saveGuest(@RequestBody Guest guest){

        List<Guest> guestList = guestService.findAll();
        System.out.println("save guest is called");
        return guestService.saveGuest(guest);
    }
}
