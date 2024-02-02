package com.example.bookingmicroservice.controller;

import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.domain.Guest;
import com.example.bookingmicroservice.service.BookingService;
import com.example.bookingmicroservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/getBooking/{bookingId}", method = RequestMethod.GET)
    public Booking getBooking(@PathVariable Integer bookingId){

        return bookingService.findById(bookingId);
    }
    @RequestMapping(value = "/saveBooking/{hotelName}", method = RequestMethod.POST)
    public Booking saveBooking(@RequestBody Booking booking, @PathVariable String hotelName){

        CompletableFuture.runAsync(()->{
            try {
                emailService.sendBookingConfirmation(booking, hotelName);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        return bookingService.saveBooking(booking);
    }

    @PutMapping(value = "/updateBooking/{bookingId}")
    public void updateBooking(@PathVariable Integer bookingId){
        System.out.println("update booking");
        bookingService.cancelBooking(bookingId);
    }

    @GetMapping(value = "/getAllBookings")
    public List<Booking> getAll(){
        bookingService.updateAll();
        return bookingService.findAll();
    }

    @GetMapping(value = "/getUpcomingBookings/{userEmail}/{isAdmin}")
    public List<Booking> getUpcomingBookings(@PathVariable String userEmail, @PathVariable Boolean isAdmin){
        return bookingService.findUpcomingBookings(userEmail, isAdmin);
    }

    @GetMapping(value = "/getCompletedBookings/{userEmail}/{isAdmin}")
    public List<Booking> getCompletedBookings(@PathVariable String userEmail, @PathVariable Boolean isAdmin){
        return bookingService.findCompletedBookings(userEmail, isAdmin);
    }

    @GetMapping(value = "/getCanceledBookings/{userEmail}/{isAdmin}")
    public List<Booking> getCanceledBookings(@PathVariable String userEmail, @PathVariable Boolean isAdmin){
        return  bookingService.findCanceledBookings(userEmail, isAdmin);
    }

}
