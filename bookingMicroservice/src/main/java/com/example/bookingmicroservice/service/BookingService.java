package com.example.bookingmicroservice.service;


import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {


    public Booking saveBooking(Booking booking);
    public Booking findById(Integer bookingId);
    public List<Booking> findAll();

    public void updateAll();

    public void cancelBooking(Integer bookingId);

    public List<Booking> findUpcomingBookings(String userEmail, Boolean isAdmin);
    public List<Booking> findCompletedBookings(String userEmail, Boolean isAdmin);
    public List<Booking> findCanceledBookings(String userEmail, Boolean isAdmin);


}
