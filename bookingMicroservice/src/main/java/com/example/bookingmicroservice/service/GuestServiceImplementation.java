package com.example.bookingmicroservice.service;

import com.example.bookingmicroservice.domain.Guest;
import com.example.bookingmicroservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImplementation implements GuestService{
    @Autowired
    GuestRepository guestRepository;

    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest findById(int guestId) {
        return guestRepository.findById(guestId).orElse(null);
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }
}
