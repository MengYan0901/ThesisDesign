package com.my.springboot.travel.controller;


import com.my.springboot.travel.dao.SiteDao;
import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.entity.Site;
import com.my.springboot.travel.model.CountryDTO;
import com.my.springboot.travel.model.SiteDTO;
import com.my.springboot.travel.service.SiteService;
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
public class SiteController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteDao siteDao;

    @GetMapping("/site/list")
    public ResponseEntity<?> showCountryList() {
        List<Site> siteList = siteService.findAll();
        List<SiteDTO> siteDTOList = new ArrayList<>();
        for (int i = 0; i < siteList.size(); i++) {
            siteDTOList.add(siteList.get(i).toSiteDTO());
        }
        return ResponseEntity.ok(siteDTOList);
    }

    @GetMapping("/site/list/sitename")
    public ResponseEntity<?> showSiteNameList() {
        List<Site> siteList = siteService.findAll();
        List<String> siteNameList = new ArrayList<>();
        for(int i = 0; i < siteList.size(); i++){
            siteNameList.add(siteList.get(i).getSiteName());
        }
        return ResponseEntity.ok(siteNameList);
    }

    @GetMapping("/site/getsitelike/{siteName}")
    public ResponseEntity<?> getSite(@PathVariable String siteName) {
        List<Site> siteList = siteService.findBySiteNameLike(siteName);
        List<SiteDTO> siteDTOList = new ArrayList<>();
        if (siteList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (siteList.size() > 0) {
                for (int i = 0; i < siteList.size(); i++) {
                    SiteDTO siteDTO = siteList.get(i).toSiteDTO();
                    siteDTO.setCode(1);
                    siteDTOList.add(siteDTO);
                }
            }
        }
        return ResponseEntity.ok(siteDTOList);
    }

    @GetMapping("/site/getsiteid/{siteName}")
    public ResponseEntity<?> getSiteIdBySiteName(@PathVariable String siteName) {
        Site newSite = siteService.findBySiteName(siteName);
        SiteDTO siteDTO = newSite.toSiteDTO();
        siteDTO.setCode(1);
        return ResponseEntity.ok(newSite.getSiteId());
    }

    @GetMapping("/site/getsitename/{siteId}")
    public ResponseEntity<?> getSiteNameBySiteId(@PathVariable int siteId) {
        Site newSite = siteService.findBySiteId(siteId);
        SiteDTO siteDTO = newSite.toSiteDTO();
        siteDTO.setCode(1);
        return ResponseEntity.ok(newSite.getSiteName());
    }
}
