package com.my.springboot.travel.dao;

import com.my.springboot.travel.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao extends JpaRepository<City,Integer> {
    City findByCityId(int cityId);
    City findByCityName(String cityName);
}
