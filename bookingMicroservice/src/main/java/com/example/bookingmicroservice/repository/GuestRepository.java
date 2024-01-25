package com.example.bookingmicroservice.repository;

import com.example.bookingmicroservice.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest,Integer> {
}
