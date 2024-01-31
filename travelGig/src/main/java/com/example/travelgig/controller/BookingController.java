package com.example.travelgig.controller;

import com.example.travelgig.client.BookingClient;
import com.example.travelgig.domain.User;
import com.example.travelgig.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class BookingController {

    @Autowired
    BookingClient bookingClient;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
    public JsonNode saveBooking(@RequestBody JsonNode booking){

        return bookingClient.saveBooking(booking);

    }

    @RequestMapping(value = "/searchBooking/{bookinglId}", method = RequestMethod.GET)
    public JsonNode searchHotelById(@PathVariable int bookingId){

        return bookingClient.searchBookingById(bookingId);
    }
    @RequestMapping(value = "/cancelBooking/{bookingId}", method = RequestMethod.PUT)
    public void updateBookingToCancel(@PathVariable int bookingId){
        bookingClient.updateBookingToCancel(bookingId);
    }

    @RequestMapping(value = "/getAllBookings", method = RequestMethod.GET)
    public JsonNode getAllBookings(){
        return bookingClient.getAllBookings();
    }

    @RequestMapping(value = "/getUpcomingBookings", method = RequestMethod.GET)
    public JsonNode getUpcomingBookings(Principal principal){
        if(principal == null){
            //error can not perform
        }
        User currentUser = userService.findByUserName(principal.getName());
        String userEmail = currentUser.getEmail();

        System.out.println("current user is " + currentUser.getUserName() );
        System.out.println("email is " + currentUser.getEmail() );

        return bookingClient.getUpcomingBookings(userEmail);
    }

    @RequestMapping(value = "/getCanceledBookings", method = RequestMethod.GET)
    public JsonNode getCanceledBookings(Principal principal){
        if(principal == null){
            //error can not perform
        }
        User currentUser = userService.findByUserName(principal.getName());
        String userEmail = currentUser.getEmail();

        System.out.println("current user is " + currentUser.getUserName() );
        System.out.println("email is " + currentUser.getEmail() );

        return bookingClient.getCanceledBookings(userEmail);
    }

    @RequestMapping(value = "/getCompletedBookings", method = RequestMethod.GET)
    public JsonNode getCompletedBookings(Principal principal){

        if(principal == null){
            //error can not perform
        }
        User currentUser = userService.findByUserName(principal.getName());
        String userEmail = currentUser.getEmail();

        System.out.println("current user is " + currentUser.getUserName() );
        System.out.println("email is " + currentUser.getEmail() );

        return bookingClient.getCompletedBookings(userEmail);
    }

    @GetMapping("/reservation")
    public ModelAndView reservation(Principal principal){
        ModelAndView mav = new ModelAndView("reservation");
        if(principal == null){
            mav.addObject("isLoggin",true);
        }else{
            mav.addObject("isLoggin",false);
        }

        return mav;
    }
}
