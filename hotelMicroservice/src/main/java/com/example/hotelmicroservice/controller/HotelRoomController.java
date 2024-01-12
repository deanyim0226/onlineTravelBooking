package com.example.hotelmicroservice.controller;


import com.example.hotelmicroservice.domain.HotelRoom;
import com.example.hotelmicroservice.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelRoomController {

    @Autowired
    HotelRoomService hotelRoomService;

    @RequestMapping(value = "/getHotelRooms", method = RequestMethod.GET)
    public List<HotelRoom> getHotelRooms(){

        return hotelRoomService.getAllHotelRooms();
    }
}
