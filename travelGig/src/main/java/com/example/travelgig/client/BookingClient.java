package com.example.travelgig.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookingClient {

    public JsonNode saveBooking(JsonNode jsonNode){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //need header to set content type

        HttpEntity<String> request = new HttpEntity<>(jsonNode.toString(),headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8181/saveBooking",request,Object.class);

        Object obj = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode booking = mapper.convertValue(obj,JsonNode.class);

        return booking;

    }
}
