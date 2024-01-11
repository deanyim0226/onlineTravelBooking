package com.example.hotelmicroservice.repository;


import com.example.hotelmicroservice.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    public List<Hotel> findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(String hotelName,String address,String city, String state);

}
