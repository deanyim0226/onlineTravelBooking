package com.example.bookingmicroservice.service;


import com.example.bookingmicroservice.domain.Guest;
import com.example.bookingmicroservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GuestService {


    public Guest saveGuest(Guest guest);

    public Guest findById(int guestId);

    public List<Guest> findAll();

}