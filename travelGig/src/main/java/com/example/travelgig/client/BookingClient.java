package com.example.travelgig.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookingClient {

    public JsonNode saveBooking(JsonNode jsonNode, String hotelName){

        System.out.println("calling saving booking from clinet");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //need header to set content type

        HttpEntity<String> request = new HttpEntity<>(jsonNode.toString(),headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8181/saveBooking/"+hotelName,request,Object.class);

        Object obj = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        //ObjectMapper provides functionality for reading and writing JSON,

        JsonNode booking = mapper.convertValue(obj,JsonNode.class);

        System.out.println(booking);
        return booking;

    }

    public JsonNode getAllBookings(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getAllBookings",Object.class);

        Object objects = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode bookings = mapper.convertValue(objects,JsonNode.class);

        System.out.println(bookings.toString());
        return  bookings;
    }

    public JsonNode getBookingById(int bookingId){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getBooking/"+ bookingId, Object.class);

        Object object = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode booking = mapper.convertValue(object, JsonNode.class);

        return booking;
    }

    public JsonNode getCompletedBookings(String userEmail, boolean isAdmin){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getCompletedBookings/"+userEmail+"/"+isAdmin,Object.class);

        Object objects = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode completedBookings = mapper.convertValue(objects,JsonNode.class);
        return completedBookings;
    }

    public JsonNode getCanceledBookings(String userEmail, boolean isAdmin){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getCanceledBookings/"+userEmail+"/"+isAdmin, Object.class);

        Object objects = responseEntity.getBody();
        ObjectMapper mapper =new ObjectMapper();

        JsonNode canceledBookings =  mapper.convertValue(objects,JsonNode.class);
        return canceledBookings;
    }

    public JsonNode getUpcomingBookings(String userEmail, boolean isAdmin){

        RestTemplate restTemplate = new RestTemplate(); //IS SYNCHRONOUS REST CLIENT PROVIDED BY THE CORE SPRING FRAMEWORK
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getUpcomingBookings/"+userEmail+"/"+isAdmin, Object.class);
        //Get consumes REST API's GET mapping response and returns domain object

        Object upcomingBookings = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonUpcomingBookings = mapper.convertValue(upcomingBookings, JsonNode.class);
        return jsonUpcomingBookings;
    }

    public void updateBookingToCancel(int bookingId){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8181/updateBooking/"+bookingId,Object.class);
    }
}
