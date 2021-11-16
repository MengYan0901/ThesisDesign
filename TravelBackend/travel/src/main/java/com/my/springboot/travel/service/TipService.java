package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.CountryDao;
import com.my.springboot.travel.dao.SiteDao;
import com.my.springboot.travel.dao.TipDao;
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

    public List<Tip> findAll() {
        return tipDao.findAll();
    }

    public void deleteTip(int tipId) {
        tipDao.deleteById(tipId);
    }

    public TipDTO updateTip(TipDTO tip) {
        Tip newTip = tipDao.findByTipName(tip.getTipName());
        newTip.setTipName(tip.getTipName());
        newTip.setTipContent(tip.getTipContent());
        newTip.setTipMark(tip.getTipMark());
        newTip.setTipPhoto(tip.getTipPhoto());
        tipDao.save(newTip);
        TipDTO tipDTO = new TipDTO();
        tipDTO.setTipId(newTip.getTipId());
        tipDTO.setTipContent(newTip.getTipContent());
        tipDTO.setTipMark(newTip.getTipMark());
        tipDTO.setTipPhoto(newTip.getTipPhoto());
        return tipDTO;
    }

    public Tip save(TipDTO tip) {
        Tip newTip = new Tip();
        newTip.setTipName(tip.getTipName());
        newTip.setTipContent(tip.getTipContent());
        newTip.setTipMark(tip.getTipMark());
        newTip.setTipPhoto(tip.getTipPhoto());
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
