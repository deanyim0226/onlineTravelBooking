package com.example.bookingmicroservice.service;


import com.example.bookingmicroservice.domain.Booking;
import com.example.bookingmicroservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {
/*
       RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity("http://localhost:8383/searchHotel/" + searchString,Object.class);

        Object objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode hotels = mapper.convertValue(objects, JsonNode.class);

        System.out.println(hotels.toString());
        return hotels;
 */

    public Booking saveBooking(Booking booking);
    public Booking findById(Integer bookingId);
    public List<Booking> findAll();

}
