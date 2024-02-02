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

    @RequestMapping(value = "/saveBooking/{hotelName}", method = RequestMethod.POST)
    public JsonNode saveBooking(@RequestBody JsonNode booking, @PathVariable String hotelName){

        return bookingClient.saveBooking(booking, hotelName);

    }

    @RequestMapping(value = "/getBooking/{bookingId}", method = RequestMethod.GET)
    public JsonNode getBooking(@PathVariable int bookingId){

        return bookingClient.getBookingById(bookingId);
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
        Boolean isAdmin = userService.isAmdin(currentUser);

        return bookingClient.getUpcomingBookings(userEmail,isAdmin);
    }

    @RequestMapping(value = "/getCanceledBookings", method = RequestMethod.GET)
    public JsonNode getCanceledBookings(Principal principal){
        if(principal == null){
            //error can not perform
        }
        User currentUser = userService.findByUserName(principal.getName());
        String userEmail = currentUser.getEmail();
        Boolean isAdmin = userService.isAmdin(currentUser);


        return bookingClient.getCanceledBookings(userEmail,isAdmin);
    }

    @RequestMapping(value = "/getCompletedBookings", method = RequestMethod.GET)
    public JsonNode getCompletedBookings(Principal principal){

        if(principal == null){
            //error can not perform
        }
        User currentUser = userService.findByUserName(principal.getName());
        String userEmail = currentUser.getEmail();
        Boolean isAdmin = userService.isAmdin(currentUser);

        return bookingClient.getCompletedBookings(userEmail,isAdmin);
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
