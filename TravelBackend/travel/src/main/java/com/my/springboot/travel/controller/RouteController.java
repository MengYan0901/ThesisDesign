package com.my.springboot.travel.controller;

import com.my.springboot.travel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/route/place")
    public ResponseEntity<?> getPlace(@RequestParam("query") String content) {
        return ResponseEntity.ok(routeService.getPlaceId(content));
        //return ResponseEntity.ok(content);
    }

    @GetMapping("/route/direction")
    public ResponseEntity<?> getDirection(@RequestParam("origin") String q1, @RequestParam("destination") String q2) {
        return ResponseEntity.ok(routeService.getDirection(q1, q2));
    }

}
