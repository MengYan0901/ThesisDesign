package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.CityDao;
import com.my.springboot.travel.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAll() {

        return cityDao.findAll();
    }

    public City findByCityName(String cityName) {

        return cityDao.findByCityName(cityName);
    }

    public City findByCityId(int cityId) {
        return cityDao.findByCityId(cityId);
    }
}
