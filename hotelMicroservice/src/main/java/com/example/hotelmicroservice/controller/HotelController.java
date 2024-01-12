package com.example.hotelmicroservice.controller;

import com.example.hotelmicroservice.domain.Hotel;
import com.example.hotelmicroservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
    public List<Hotel> searchHotel(@PathVariable String searchString){

        return hotelService.searchHotel(searchString);
    }

    @RequestMapping(value = "/searchHotelById/{hotelId}", method = RequestMethod.GET)
    public Hotel searchHotelById(@PathVariable int hotelId){

        return hotelService.searchHotelById(hotelId);
    }
}
