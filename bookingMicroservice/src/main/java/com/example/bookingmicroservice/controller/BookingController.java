package com.example.bookingmicroservice.controller;

import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
    public Booking saveBooking(@RequestBody Booking booking){

        return bookingService.saveBooking(booking);
    }

}
