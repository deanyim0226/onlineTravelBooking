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
public class GuestClient {


    public JsonNode saveGuest(JsonNode json){
        System.out.println("saving guest is called");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json.toString(),headers);


        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(
                "http://localhost:8181/saveGuest",request,Object.class);

        Object object = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode guest = mapper.convertValue(object,JsonNode.class);


        return guest;
    }
}
