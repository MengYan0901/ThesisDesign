package com.my.springboot.travel.dao;

import com.my.springboot.travel.entity.Like;
import com.my.springboot.travel.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LikeDao extends JpaRepository<Like,Integer> {

    Like findByLikeId(Integer likeId);

    List<Like> findByTipTipId(Integer tipId);

    List<Like> findBySiteSiteId(Integer siteId);

    List<Like> findByCityCityId(Integer cityId);

    List<Like> findByUserUserId(Integer userId);
}
