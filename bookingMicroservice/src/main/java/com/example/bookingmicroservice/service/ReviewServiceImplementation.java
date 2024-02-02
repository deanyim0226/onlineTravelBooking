package com.example.bookingmicroservice.service;

import com.example.bookingmicroservice.domain.Review;
import com.example.bookingmicroservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<Review> findReviewByHotelId(Integer hotelId) {
        List<Review> reviewList = reviewRepository.findAll();
        List<Review> filteredList = new ArrayList<>();

        for(Review review : reviewList){

            if(review.getBooking().getHotelId() == hotelId){
                filteredList.add(review);
            }
        }

        return filteredList;
    }
}
