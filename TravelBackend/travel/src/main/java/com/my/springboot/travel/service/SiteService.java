package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.SiteDao;
import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.entity.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteDao siteDao;

    public List<Site> findBySiteNameLike(String SiteName) {

        return siteDao.findBySiteNameLike("%" + SiteName + "%");

    }


    public Site findBySiteName(String siteName) {

        return siteDao.findBySiteName(siteName);

    }

    public List<Site> findAll() {

        return siteDao.findAll();
    }

    public Site findBySiteId(int siteId){
        return siteDao.findBySiteId(siteId);
    }

}
