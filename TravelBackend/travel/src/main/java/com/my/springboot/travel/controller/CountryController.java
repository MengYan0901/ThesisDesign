package com.my.springboot.travel.controller;

import com.my.springboot.travel.dao.CountryDao;
import com.my.springboot.travel.entity.City;
import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.model.CountryDTO;
import com.my.springboot.travel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryDao countryDao;

    @GetMapping("/country/list")
    public ResponseEntity<?> showCountryList() {
        List<Country> countryList = countryService.findAll();
        List<CountryDTO> countryDTOList = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++) {
            countryDTOList.add(countryList.get(i).toCountryDTO());
        }
        return ResponseEntity.ok(countryDTOList);
    }

    @GetMapping("/country/list/countryname")
    public ResponseEntity<?> showCountryNameList() {
        List<Country> countryList = countryService.findAll();
        List<String> countryNameList = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++) {
            countryNameList.add(countryList.get(i).getCountryName());
        }
        return ResponseEntity.ok(countryNameList);
    }

    @GetMapping("/country/getcountrylike/{countryName}")
    public ResponseEntity<?> getCountryListByCountryName(@PathVariable String countryName) {
        List<Country> countryList = countryService.findByCountryNameLike(countryName);
        List<CountryDTO> countryDTOList = new ArrayList<>();
        if (countryList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (countryList.size() > 0) {
                for (int i = 0; i < countryList.size(); i++) {
                    CountryDTO countryDTO = countryList.get(i).toCountryDTO();
                    countryDTO.setCode(1);
                    countryDTOList.add(countryDTO);
                }
            }
        }
        return ResponseEntity.ok(countryDTOList);
    }

    @GetMapping("/country/getcountryid/{countryName}")
    public ResponseEntity<?> getCountryIdByCountryName(@PathVariable String countryName) {
        Country newCountry = countryService.findByCountryName(countryName);
        CountryDTO countryDTO = newCountry.toCountryDTO();
        countryDTO.setCode(1);
        return ResponseEntity.ok(newCountry.getCountryId());
    }

    @GetMapping("/country/getcountryname/{countryId}")
    public ResponseEntity<?> getCountryNameByCountryId(@PathVariable int countryId) {
        Country newCountry = countryService.findByCountryId(countryId);
        CountryDTO countryDTO = newCountry.toCountryDTO();
        countryDTO.setCode(1);
        return ResponseEntity.ok(newCountry.getCountryName());
    }


}
