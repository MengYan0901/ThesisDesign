package com.my.springboot.travel.controller;

import com.my.springboot.travel.dao.CityDao;
import com.my.springboot.travel.entity.City;
import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.model.CityDTO;
import com.my.springboot.travel.model.CountryDTO;
import com.my.springboot.travel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city/list/cityname")
    public ResponseEntity<?> showCityNameList() {
        List<City> cityList = cityService.findAll();
        List<String> cityNameList = new ArrayList<>();
        for(int i = 0; i < cityList.size(); i++){
            cityNameList.add(cityList.get(i).getCityName());
        }
        return ResponseEntity.ok(cityNameList);
    }

    @GetMapping("/city/getcityid/{cityName}")
    public ResponseEntity<?> getCityIdByCityName(@PathVariable String cityName) {
        City newCity = cityService.findByCityName(cityName);
        CityDTO cityDTO = newCity.toCityDTO();
        cityDTO.setCode(1);
        return ResponseEntity.ok(newCity.getCityId());
    }

    @GetMapping("/city/getcityname/{cityId}")
    public ResponseEntity<?> getCityNameByCityId(@PathVariable int cityId) {
        City newCity = cityService.findByCityId(cityId);
        CityDTO cityDTO = newCity.toCityDTO();
        cityDTO.setCode(1);
        return ResponseEntity.ok(newCity.getCityName());
    }
}
