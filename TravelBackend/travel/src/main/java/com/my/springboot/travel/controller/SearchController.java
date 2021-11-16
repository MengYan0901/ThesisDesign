package com.my.springboot.travel.controller;

import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.entity.Site;
import com.my.springboot.travel.model.SearchDTO;
import com.my.springboot.travel.service.CountryService;
import com.my.springboot.travel.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class SearchController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private SiteService siteService;

    @GetMapping("/search/{search}")
    public ResponseEntity<?> selectSite(@PathVariable String search) {

        List<Country> countryList = countryService.findByCountryNameLike(search);
        List<Site> siteList = siteService.findBySiteNameLike(search);
        List<SearchDTO> searchDTOList = new ArrayList<>();
        if (countryList.size()==0 && siteList.size()==0) {
            return ResponseEntity.ok(0);
        } else {
            if (countryList.size() > 0 ) {
                for(int i = 0; i< countryList.size(); i++) {
                    SearchDTO sr = new SearchDTO();
                    sr.setCode(1);
                    sr.setCountryName(countryList.get(i).getCountryName());
                    sr.setCountryAddress(countryList.get(i).getCountryAddress());
                    sr.setCountryPhoto(countryList.get(i).getCountryPhoto());
                    searchDTOList.add(sr);
                }
            }
            if (siteList.size() > 0 ) {
                for(int i = 0; i< siteList.size(); i++) {
                    SearchDTO sr = new SearchDTO();
                    sr.setCode(1);
                    sr.setSiteName(siteList.get(i).getSiteName());
                    sr.setSiteAddress(siteList.get(i).getSiteAddress());
                    sr.setSitePhoto(siteList.get(i).getSitePhoto());
                    searchDTOList.add(sr);
                }
            }
        }
        return ResponseEntity.ok(searchDTOList);
    }

}
