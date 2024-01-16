package com.example.hotelmicroservice.service;

import com.example.hotelmicroservice.domain.Hotel;
import com.example.hotelmicroservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {


    @Autowired
    HotelRepository hotelRepository;

    public List<Hotel> searchHotel(String searchString){
        List<Hotel> listHotel = hotelRepository.findAll();
        for(Hotel hotel :listHotel){
            System.out.println(hotel.getHotelName());
        }

        searchString = "%"+ searchString + "%";
        return hotelRepository.findByHotelNameLikeIgnoreCaseOrAddressLikeIgnoreCaseOrCityLikeIgnoreCaseOrStateLikeIgnoreCase(searchString,searchString,searchString,searchString);
    }

    public Hotel searchHotelById(int hotelId){

        return hotelRepository.findById(hotelId).orElse(null);
    }
}
