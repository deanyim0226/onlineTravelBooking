package com.example.bookingmicroservice.controller;

import com.example.bookingmicroservice.domain.Review;
import com.example.bookingmicroservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public Review saveReview(@RequestBody Review review){


        return reviewService.saveReview(review);
    }

    @RequestMapping(value = "/getReviews/{hotelId}", method = RequestMethod.GET)
    public List<Review> getReviews(@PathVariable Integer hotelId){



        return reviewService.findReviewByHotelId(hotelId);
    }
}
