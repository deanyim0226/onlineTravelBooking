package com.example.travelgig.controller;

import com.example.travelgig.client.ReviewClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class ReviewController {

    @Autowired
    ReviewClient reviewClient;

    @RequestMapping(value = "saveReview", method = RequestMethod.POST)
    public JsonNode saveReview(@RequestBody JsonNode review){

        return reviewClient.saveReview(review);
    }

    @RequestMapping(value = "getReviews/{hotelId}", method = RequestMethod.GET)
    public JsonNode getReviewsById(@PathVariable Integer hotelId){

        return reviewClient.getReviewsById(hotelId);
    }

    @RequestMapping("/review")
    public ModelAndView review(@RequestParam Integer hotelId, @RequestParam String hotelName, Principal principal){
        ModelAndView mav = new ModelAndView("review");

        mav.addObject("hotelId", hotelId);
        mav.addObject("hotelName", hotelName);

        if(principal == null){
            mav.addObject("isLoggin",true);
        }else{
            mav.addObject("isLoggin",false);
        }

        return mav;
    }
}
