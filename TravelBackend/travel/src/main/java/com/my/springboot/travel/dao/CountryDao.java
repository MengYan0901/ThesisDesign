package com.my.springboot.travel.dao;

import com.my.springboot.travel.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryDao extends JpaRepository<Country,Integer> {

    List<Country> findByCountryNameLike(String countryName);
    Country findByCountryId(int countryId);
    Country findByCountryName(String countryName);
}
