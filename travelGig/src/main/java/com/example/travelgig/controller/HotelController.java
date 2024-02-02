package com.example.travelgig.controller;

import com.example.travelgig.client.HotelClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    @Autowired
    HotelClient hotelClient;

    @RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
    public JsonNode searchHotel(@PathVariable String searchString){

        return hotelClient.searchHotel(searchString);
    }

    @RequestMapping(value = "/searchHotelById/{hotelId}", method = RequestMethod.GET)
    public JsonNode searchHotelById(@PathVariable int hotelId){

        return hotelClient.searchHotelById(hotelId);
    }
}
