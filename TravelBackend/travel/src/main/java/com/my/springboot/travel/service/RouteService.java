package com.my.springboot.travel.service;

import com.my.springboot.travel.model.RoutePlaceDTO;
import com.my.springboot.travel.model.TicketTokenDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class RouteService {

    private final RestTemplate restTemplate;

    public RouteService(RestTemplateBuilder restTemplateBuilder) {


        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPlaceId(String queryparams) {

        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                // Add query parameter
                .queryParam("key", "AIzaSyDD9VLdCCwDcNmHJ35To-r083_UpJF1314")
                .queryParam("query", queryparams);

        // send POST request
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        // check response
        System.out.println("Request Successful");
        return response.getBody();
    }

    public String getDirection(String queryparams1, String queryparams2) {

        String url = "https://maps.googleapis.com/maps/api/directions/json";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                // Add query parameter
                .queryParam("key", "AIzaSyDD9VLdCCwDcNmHJ35To-r083_UpJF1314")
                .queryParam("mode","DRIVING")
                .queryParam("origin", queryparams1)
                .queryParam("destination", queryparams2);

        // send POST request
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        // check response
        System.out.println("Request Successful");
        return response.getBody();
    }
}

