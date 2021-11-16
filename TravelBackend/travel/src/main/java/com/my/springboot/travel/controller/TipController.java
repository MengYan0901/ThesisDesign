package com.my.springboot.travel.controller;

import com.my.springboot.travel.entity.Tip;
import com.my.springboot.travel.model.TipDTO;
import com.my.springboot.travel.service.CountryService;
import com.my.springboot.travel.service.SiteService;
import com.my.springboot.travel.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class TipController {

    private int code = 0;

    @Autowired
    private TipService tipService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private SiteService siteService;

    @GetMapping("/tip/list")
    public ResponseEntity<?> showTipList() {
        List<Tip> tipList = tipService.findAll();
        List<TipDTO> tipDTOList = new ArrayList<>();
        for (int i = 0; i < tipList.size(); i++) {
            tipDTOList.add(tipList.get(i).toTipDTO());
        }
        return ResponseEntity.ok(tipDTOList);
    }

    @PostMapping("/tip/add")
    public ResponseEntity<?> addTip(@RequestBody TipDTO tip) {

        return ResponseEntity.ok(tipService.save(tip));
    }

    @PostMapping("/tip/update")
    public ResponseEntity<?> updateTip(@RequestBody TipDTO tip) {

        return ResponseEntity.ok(tipService.updateTip(tip));
    }

    @DeleteMapping(value = "/tip/delete/{tipId}")
    public ResponseEntity<?> deleteTip(@PathVariable int tipId) {
        tipService.deleteTip(tipId);
        return ResponseEntity.ok(1);
    }

    @GetMapping("/tip/search/byuserid/{userId}")
    public ResponseEntity<?> selectTipByUserId(@PathVariable int userId) {
        TipDTO tip = new TipDTO(0);
        List<Tip> tipList = tipService.findTipByUserId(userId);
        List<TipDTO> tipDTOList = new ArrayList<>();
        if (tipList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (tipList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < tipList.size(); i++) {
                    TipDTO tipDTO = tipList.get(i).toTipDTO();
                    tipDTO.setCode(this.code);
                    tipDTO.setUserId(tipList.get(i).getUser().getUserId());
                    tipDTOList.add(tipDTO);
                }
            }
        }
        return ResponseEntity.ok(tipDTOList);
    }

    @GetMapping("/tip/search/bycountryid/{countryId}")
    public ResponseEntity<?> selectTipByCountryId(@PathVariable int countryId) {
        TipDTO tip = new TipDTO(0);
        List<Tip> tipList = tipService.findTipByCountryId(countryId);
        List<TipDTO> tipDTOList = new ArrayList<>();
        if (tipList == null) {
            this.code = 0;
        } else {
            if (tipList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < tipList.size(); i++) {
                    TipDTO tipDTO = tipList.get(i).toTipDTO();
                    tipDTO.setCode(this.code);
                    tipDTO.setCountryId(tipList.get(i).getCountry().getCountryId());
                    tipDTOList.add(tipDTO);
                }
            }
        }
        return ResponseEntity.ok(tipDTOList);
    }

    @GetMapping("/tip/search/bysiteid/{siteId}")
    public ResponseEntity<?> selectTipBySiteId(@PathVariable int siteId) {
        TipDTO tip = new TipDTO(0);
        List<Tip> tipList = tipService.findTipBySiteId(siteId);
        List<TipDTO> tipDTOList = new ArrayList<>();
        if (tipList == null) {
            this.code = 0;
        } else {
            if (tipList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < tipList.size(); i++) {
                    TipDTO tipDTO = tipList.get(i).toTipDTO();
                    tipDTO.setCode(this.code);
                    tipDTO.setSiteId(tipList.get(i).getSite().getSiteId());
                    tipDTOList.add(tipDTO);
                }
            }
        }
        return ResponseEntity.ok(tipDTOList);
    }
}
