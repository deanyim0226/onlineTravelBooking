package com.example.hotelmicroservice.repository;

import com.example.hotelmicroservice.domain.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom,Integer> {
}
