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
public class ReviewClient {

    public JsonNode saveReview(JsonNode jsonNode){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonNode.toString(),headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8181/saveReview", request, Object.class);

        Object obj = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode review = mapper.convertValue(obj,JsonNode.class);

        return review;
    }

    public JsonNode getReviewsById(int hotelId){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8181/getReviews/"+hotelId,Object.class);
        Object obj = responseEntity.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode reviews = mapper.convertValue(obj,JsonNode.class);

        return reviews;
    }
}
