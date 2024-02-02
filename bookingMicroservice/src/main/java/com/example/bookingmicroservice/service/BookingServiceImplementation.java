package com.example.bookingmicroservice.service;

import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Override
    public void updateAll() {
        List<Booking> bookingList = bookingRepository.findAll();
        LocalDate today = LocalDate.now();

        for(Booking booking : bookingList){

            if(today.isAfter(booking.getCheckInDate())){

                if(booking.getStatus().equals("UPCOMING")){
                    booking.setStatus("COMPLETED");
                    bookingRepository.save(booking);
                }
            }
        }
    }

    @Override
    public void cancelBooking(Integer bookingId) {
        Booking retrievedBooking = bookingRepository.findById(bookingId).orElse(null);

        if(retrievedBooking != null){
            retrievedBooking.setStatus("CANCELED");
            bookingRepository.save(retrievedBooking);
        }
    }


    @Override
    public List<Booking> findUpcomingBookings(String userEmail, Boolean isAdmin) {
        updateAll();

        List<Booking> bookingList = bookingRepository.findAll();
        List<Booking> upcomingBookings = new ArrayList<>();

        for(Booking booking : bookingList){

            //authentication
            if(isAdmin){
                if(booking.getStatus().equals("UPCOMING")){
                    upcomingBookings.add(booking);
                }
            }else{
                if(booking.getUserEmail().equals(userEmail)){
                    if(booking.getStatus().equals("UPCOMING")){
                        upcomingBookings.add(booking);
                    }
                }
            }


        }
        return upcomingBookings;
    }

    @Override
    public List<Booking> findCompletedBookings(String userEmail, Boolean isAdmin) {
        updateAll();

        List<Booking> bookingList = bookingRepository.findAll();
        List<Booking> completedBookings = new ArrayList<>();

        for(Booking booking : bookingList){

            if(isAdmin){

                if(booking.getStatus().equals("COMPLETED")){
                    completedBookings.add(booking);
                }

            }else{
                if(booking.getUserEmail().equals(userEmail)){
                    if(booking.getStatus().equals("COMPLETED")){
                        completedBookings.add(booking);
                    }
                }
            }


        }
        return completedBookings;
    }

    @Override
    public List<Booking> findCanceledBookings(String userEmail, Boolean isAdmin) {
        updateAll();

        List<Booking> bookingList = bookingRepository.findAll();
        List<Booking> canceledBookings = new ArrayList<>();

        for(Booking booking : bookingList){

            if(isAdmin){

                if(booking.getStatus().equals("CANCELED")){
                    canceledBookings.add(booking);
                }
            }else{
                if(booking.getUserEmail().equals(userEmail)){
                    if(booking.getStatus().equals("CANCELED")){
                        canceledBookings.add(booking);
                    }
                }
            }

        }
        return canceledBookings;
    }


}
