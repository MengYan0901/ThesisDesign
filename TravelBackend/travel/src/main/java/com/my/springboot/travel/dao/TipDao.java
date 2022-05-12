package com.my.springboot.travel.dao;

import com.my.springboot.travel.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipDao extends JpaRepository<Tip,Integer> {
    Tip findByTipName(String tipName);

    Tip findByTipId(int tipId);

    List<Tip> findByUserUserId(Integer userId);

    List<Tip> findByCountryCountryId(Integer countryId);

    List<Tip> findBySiteSiteId(Integer siteId);
}