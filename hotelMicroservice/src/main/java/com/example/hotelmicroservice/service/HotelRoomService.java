package com.example.hotelmicroservice.service;

import com.example.hotelmicroservice.domain.Hotel;
import com.example.hotelmicroservice.domain.HotelRoom;
import com.example.hotelmicroservice.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomService {
    @Autowired
    HotelRoomRepository hotelRoomRepository;

    public List<HotelRoom> getAllHotelRooms(){

        return hotelRoomRepository.findAll();
    }
}
