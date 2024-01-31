package com.example.bookingmicroservice.controller;

import com.example.bookingmicroservice.domain.Review;
import com.example.bookingmicroservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public Review saveReview(@RequestBody Review review){


        return reviewService.saveReview(review);
    }
}
