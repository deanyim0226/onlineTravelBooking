package com.example.bookingmicroservice.service;

import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingServiceImplementation implements BookingService{
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public Booking saveBooking(Booking booking){

        return bookingRepository.save(booking);
    }

    @Override
    public Booking findById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> findAll(){

        return bookingRepository.findAll();
    }
}
