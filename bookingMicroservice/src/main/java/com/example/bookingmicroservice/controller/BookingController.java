package com.example.bookingmicroservice.controller;

import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.domain.Guest;
import com.example.bookingmicroservice.service.BookingService;
import com.example.bookingmicroservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
    public Booking saveBooking(@RequestBody Booking booking){

        CompletableFuture.runAsync(()->{
            try {
                emailService.sendBookingConfirmation(booking);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return bookingService.saveBooking(booking);
    }

}
