package com.my.springboot.travel.dao;

import com.my.springboot.travel.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteDao extends JpaRepository<Site,Integer> {
    List<Site> findBySiteNameLike(String siteName);
}
