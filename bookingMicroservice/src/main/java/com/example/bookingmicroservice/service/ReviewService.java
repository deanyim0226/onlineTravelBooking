package com.example.bookingmicroservice.service;


import com.example.bookingmicroservice.domain.Review;

import java.util.List;

public interface ReviewService {

    public Review saveReview(Review review);
    public Review findById(Integer reviewId);
    public List<Review> findAll();
}
