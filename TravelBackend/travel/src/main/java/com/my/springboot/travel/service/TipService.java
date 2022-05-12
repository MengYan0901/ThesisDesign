package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.*;
import com.my.springboot.travel.entity.Tip;
import com.my.springboot.travel.model.TipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipService {

    @Autowired
    private TipDao tipDao;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityDao cityDao;

    public List<Tip> findAll() {
        return tipDao.findAll();
    }

    public void deleteTip(int tipId) {
        tipDao.deleteById(tipId);
    }

    public TipDTO updateTip(TipDTO tip) {
        Tip newTip = tipDao.findByTipId(tip.getTipId());
        newTip.setTipName(tip.getTipName());
        newTip.setTipContent(tip.getTipContent());
        newTip.setTipMark(tip.getTipMark());
        newTip.setTipPhoto(tip.getTipPhoto());
        newTip.setUser(userDao.findByUserId(tip.getUserId()));
        newTip.setSite(siteDao.findBySiteId(tip.getSiteId()));
        newTip.setCountry(countryDao.findByCountryId(tip.getCountryId()));
        newTip.setCity(cityDao.findByCityId(tip.getCityId()));
        tipDao.save(newTip);
        TipDTO tipDTO = new TipDTO();
        tipDTO.setTipId(newTip.getTipId());
        tipDTO.setTipName(newTip.getTipName());
        tipDTO.setTipContent(newTip.getTipContent());
        tipDTO.setTipMark(newTip.getTipMark());
        tipDTO.setTipPhoto(newTip.getTipPhoto());
        tipDTO.setUserId(newTip.getUser().getUserId());
        tipDTO.setSiteId(newTip.getSite().getSiteId());
        tipDTO.setCountryId(newTip.getCountry().getCountryId());
        tipDTO.setCityId(newTip.getCity().getCityId());
        return tipDTO;
    }

    public Tip save(TipDTO tip) {
        Tip newTip = new Tip();
        newTip.setTipName(tip.getTipName());
        newTip.setTipContent(tip.getTipContent());
        newTip.setTipMark(tip.getTipMark());
        newTip.setTipPhoto(tip.getTipPhoto());
        newTip.setUser(userDao.findByUserId(tip.getUserId()));
        newTip.setSite(siteDao.findBySiteId(tip.getSiteId()));
        newTip.setCountry(countryDao.findByCountryId(tip.getCountryId()));
        newTip.setCity(cityDao.findByCityId(tip.getCityId()));
        return tipDao.save(newTip);
    }

    public List<Tip> findTipByUserId(int userId) {

        return tipDao.findByUserUserId(userId);
    }

    public List<Tip> findTipByCountryId(int countryId) {
        return tipDao.findByCountryCountryId(countryId);
    }

    public List<Tip> findTipBySiteId(int siteId) {

         return tipDao.findBySiteSiteId(siteId);
    }

}
