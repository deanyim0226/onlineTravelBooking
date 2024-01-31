package com.example.bookingmicroservice.service;

import com.example.bookingmicroservice.domain.Review;
import com.example.bookingmicroservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Integer reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
