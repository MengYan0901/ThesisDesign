package com.my.springboot.travel.controller;


import com.my.springboot.travel.entity.Site;
import com.my.springboot.travel.model.CountryDTO;
import com.my.springboot.travel.model.SiteDTO;
import com.my.springboot.travel.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/site/list")
    public ResponseEntity<?> showCountryList(){
        List<Site> siteList = siteService.findAll();
        List<SiteDTO> siteDTOList = new ArrayList<>();
        for (int i = 0; i < siteList.size(); i++) {
            SiteDTO siteDTO=new SiteDTO();
            siteDTO.setSiteId(siteList.get(i).getSiteId());
            siteDTO.setSiteName(siteList.get(i).getSiteName());
            siteDTO.setSiteAddress(siteList.get(i).getSiteAddress());
            siteDTO.setSitePhoto(siteList.get(i).getSitePhoto());
            siteDTOList.add(siteDTO);
        }
        return ResponseEntity.ok(siteDTOList);

    }
}
