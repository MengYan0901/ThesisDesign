package com.my.springboot.travel.controller;

import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.entity.Site;
import com.my.springboot.travel.model.SearchResponse;
import com.my.springboot.travel.service.CountryService;
import com.my.springboot.travel.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class SearchController {

    private int code = 0;

    @Autowired
    private CountryService countryService;

    @Autowired
    private SiteService siteService;

    @GetMapping("/search/{search}")
    public ResponseEntity<?> selectSite(@PathVariable String search) {

        SearchResponse sr = new SearchResponse(0);
        List<Country> countryList = countryService.findByCountryName(search);
        List<Site> siteList = siteService.findBySiteName(search);
        if (countryList == null && siteList == null) {
            this.code = 0;
        } else {
            if (countryList.size() > 0) {
                this.code = 1;
                Country c = countryList.get(0);
                sr.setCode(this.code);
                sr.setCountryName(c.getCountryName());
                sr.setCountryAddress(c.getCountryAddress());
                sr.setCountryPhoto(c.getCountryPhoto());
            }
            if (siteList.size() > 0) {
                this.code = 1;
                Site s = siteList.get(0);
                sr.setCode(this.code);
                sr.setSiteName(s.getSiteName());
                sr.setSiteAddress(s.getSiteAddress());
                sr.setSitePhoto(s.getSitePhoto());
            }
        }
        return ResponseEntity.ok(sr);

    }


}
