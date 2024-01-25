package com.example.travelgig.controller;


import com.example.travelgig.client.GuestClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

    @Autowired
    GuestClient guestClient;

    @RequestMapping(value = "/saveGuest", method = RequestMethod.POST)
    public JsonNode saveGuest(@RequestBody JsonNode guest){
        System.out.println("saving guest is called as JSON");
        return guestClient.saveGuest(guest);
    }
}
