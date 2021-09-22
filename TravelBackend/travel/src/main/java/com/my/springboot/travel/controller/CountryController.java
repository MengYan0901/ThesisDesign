package com.my.springboot.travel.controller;

import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.model.CountryDTO;
import com.my.springboot.travel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country/list")
    public ResponseEntity<?> showCountryList() {
        List<Country> countryList = countryService.findAll();
        List<CountryDTO> countryDTOList = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++) {
            CountryDTO countryDTO=new CountryDTO();
            countryDTO.setCountryId(countryList.get(i).getCountryId());
            countryDTO.setCountryName(countryList.get(i).getCountryName());
            countryDTO.setCountryAddress(countryList.get(i).getCountryAddress());
            countryDTO.setCountryPhoto(countryList.get(i).getCountryPhoto());
            countryDTOList.add(countryDTO);
        }

        return ResponseEntity.ok(countryDTOList);

    }


}
