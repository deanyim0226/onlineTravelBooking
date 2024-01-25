package com.example.travelgig.controller;

import com.example.travelgig.client.BookingClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    BookingClient bookingClient;

    @RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
    public JsonNode saveBooking(@RequestBody JsonNode booking){

        return bookingClient.saveBooking(booking);

    }
}
