package com.example.travelgig.controller;

import com.example.travelgig.client.HotelRoomClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRoomController {

    @Autowired
    HotelRoomClient hotelRoomClient;

    @RequestMapping(value = "/getHotelRooms", method = RequestMethod.GET)
    public JsonNode getHotelRooms(){

        return hotelRoomClient.getHotelRooms();
    }
}
