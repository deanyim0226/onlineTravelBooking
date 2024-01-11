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
        JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);

        System.out.println(returnObj.toString());
        return returnObj;
    }
}
