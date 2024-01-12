package com.example.travelgig.client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HotelClient {

    public JsonNode searchHotel(String searchString){


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity("http://localhost:8383/searchHotel/" + searchString,Object.class);

        Object objects = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode hotels = mapper.convertValue(objects, JsonNode.class);

        System.out.println(hotels.toString());
        return hotels;
    }

    public JsonNode searchHotelById(int hotelId){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/searchHotelById/"+ hotelId, Object.class);

        Object object = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode hotel = mapper.convertValue(object, JsonNode.class);

        return hotel;
    }
}
