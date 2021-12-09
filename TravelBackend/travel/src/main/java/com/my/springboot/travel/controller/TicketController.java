package com.my.springboot.travel.controller;


import com.my.springboot.travel.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket/token")
    public ResponseEntity<?> getToken() {
        return ResponseEntity.ok(ticketService.getToken());
    }


    @RequestMapping(value = "/ticket/list", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> showTicketList(HttpEntity<String> httpEntity) {
        String ticketRequest = httpEntity.getBody();
        System.out.print(ticketRequest);
        return ResponseEntity.ok(ticketService.showTicketList(ticketRequest));
    }
}
