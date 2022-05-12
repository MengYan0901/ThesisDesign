package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.*;
import com.my.springboot.travel.entity.Like;
import com.my.springboot.travel.model.LikeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private TipDao tipDao;

    @Autowired
    private UserDao userDao;


    public List<Like> findAll() {
        return likeDao.findAll();
    }

    public LikeDTO updateLike(LikeDTO like){
        Like newLike = likeDao.findByLikeId(like.getLikeId());
        newLike.setLikeCity(like.getLikeCity());
        newLike.setLikeSite(like.getLikeSite());
        newLike.setLikeTip(like.getLikeTip());
        newLike.setUser(userDao.findByUserId(like.getUserId()));
        newLike.setCity(cityDao.findByCityId(like.getCityId()));
        newLike.setTip(tipDao.findByTipId(like.getTipId()));
        newLike.setSite(siteDao.findBySiteId(like.getSiteId()));
        likeDao.save(newLike);
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setCityId(newLike.getLikeId());
        likeDTO.setLikeCity(newLike.getLikeCity());
        likeDTO.setLikeSite(newLike.getLikeSite());
        likeDTO.setLikeTip(newLike.getLikeTip());
        likeDTO.setUserId(newLike.getUser().getUserId());
        likeDTO.setTipId(newLike.getTip().getTipId());
        likeDTO.setSiteId(newLike.getSite().getSiteId());
        likeDTO.setCityId(newLike.getCity().getCityId());
        return likeDTO;
    }

    public Like save(LikeDTO like){
        Like newLike = new Like();
        newLike.setLikeTip(like.getLikeTip());
        newLike.setLikeSite(like.getLikeSite());
        newLike.setLikeCity(like.getLikeCity());
        newLike.setUser(userDao.findByUserId(like.getUserId()));
        newLike.setCity(cityDao.findByCityId(like.getCityId()));
        newLike.setTip(tipDao.findByTipId(like.getTipId()));
        newLike.setSite(siteDao.findBySiteId(like.getSiteId()));
        return likeDao.save(newLike);
    }

    public List<Like> findByTipId(int tipId) {

        return likeDao.findByTipTipId(tipId);
    }

    public List<Like> findBySiteId(int siteId) {

        return likeDao.findBySiteSiteId(siteId);
    }

    public List<Like> findByCityId(int cityId) {

        return likeDao.findByCityCityId(cityId);
    }

    public List<Like> findByUserId(int userId) {
        return likeDao.findByUserUserId(userId);
    }
}
