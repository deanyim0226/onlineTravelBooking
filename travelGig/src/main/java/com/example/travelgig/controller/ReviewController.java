package com.example.travelgig.controller;

import com.example.travelgig.client.ReviewClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewClient reviewClient;
}
