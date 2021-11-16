package com.my.springboot.travel.service;

import com.my.springboot.travel.model.TicketTokenDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class TicketService {

    private final RestTemplate restTemplate;

    public TicketService(RestTemplateBuilder restTemplateBuilder) {


        this.restTemplate = restTemplateBuilder.build();
    }

    public TicketTokenDTO getToken() {
        String url = "https://test.api.amadeus.com/v1/security/oauth2/token";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));

        // create a map for post parameters
//        Map<String, Object> map = new HashMap<>();
//        map.put("grant_type", "client_credentials");
//        map.put("client_id", "nMr830YJRAgQG9W24q9SYak9GGCH8pI6");
//        map.put("client_secret", "ZaOdF5szqpu4nktq");
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", "nMr830YJRAgQG9W24q9SYak9GGCH8pI6");
        params.add("client_secret", "ZaOdF5szqpu4nktq");


        // build the request
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(params, headers);

        // send POST request
        ResponseEntity<TicketTokenDTO> response = this.restTemplate.postForEntity(url, entity, TicketTokenDTO.class);

        // check response status code
//        if (response.getStatusCode() == HttpStatus.CREATED) {
        return response.getBody();

//        } else {
//            return null;
//        }
    }

    public String showTicketList(String ticketRequest) {
        String url = "https://test.api.amadeus.com/v2/shopping/flight-offers";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(this.getToken().getAccess_token());
        headers.add("X-HTTP-Method-Override", "GET");

        // build the request
        HttpEntity<String> request = new HttpEntity<>(ticketRequest, headers);


        // send POST request
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, request, String.class);
//        if (response.getStatusCode() == HttpStatus.CREATED) {
        return response.getBody();
//        } else {
//            return null;
//        }
    }
}
