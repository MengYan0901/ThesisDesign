package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.CountryDao;
import com.my.springboot.travel.dao.SiteDao;
import com.my.springboot.travel.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;

    public List<Country> findByCountryNameLike(String CountryName) {

        return countryDao.findByCountryNameLike("%" + CountryName + "%");

    }

    public Country findByCountryName(String CountryName) {

        return countryDao.findByCountryName(CountryName);

    }

    public List<Country> findAll() {

        return countryDao.findAll();
    }

    public Country findByCountryId(int countryId){
        return countryDao.findByCountryId(countryId);
    }

}
