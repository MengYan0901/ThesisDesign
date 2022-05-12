package com.my.springboot.travel.controller;

import com.my.springboot.travel.dao.LikeDao;
import com.my.springboot.travel.entity.DAOComment;
import com.my.springboot.travel.entity.Like;
import com.my.springboot.travel.model.CommentDTO;
import com.my.springboot.travel.model.LikeDTO;
import com.my.springboot.travel.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class LikeController {

    private int code = 0;
    private int likeNumber = 0;

    @Autowired
    private LikeService likeService;

    @Autowired
    private LikeDao likeDao;

    @GetMapping("/like/list")
    public ResponseEntity<?> showLikeList() {
        List<Like> likeList = likeService.findAll();
        List<LikeDTO> likeDTOList = new ArrayList<>();
        for (int i = 0; i < likeList.size(); i++) {
            LikeDTO likeDTO = likeList.get(i).toLikeDTO();
            likeDTO.setUserId(likeList.get(i).getUser().getUserId());
            likeDTO.setTipId(likeList.get(i).getTip().getTipId());
            likeDTO.setCityId(likeList.get(i).getCity().getCityId());
            likeDTO.setSiteId(likeList.get(i).getSite().getSiteId());
            likeDTOList.add(likeDTO);
        }
        return ResponseEntity.ok(likeDTOList);
    }

    @PostMapping("/like/update")
    public ResponseEntity<?> updateLike(@RequestBody LikeDTO likeDTO) {

        List<Like> likeList1 = likeService.findByUserId(likeDTO.getUserId());
        this.code = 0;
        for (int i = 0; i < likeList1.size(); i++) {
            int id = likeList1.get(i).getTip().getTipId();
            if (id == likeDTO.getTipId()) {
                likeDTO.setLikeId(likeList1.get(i).getLikeId());
                this.code = 1;
                break;
            } else {
                this.code = 0;
            }
        }
        if (this.code == 1) {
            System.out.println("update" + likeDTO);
            likeService.updateLike(likeDTO);
            if (likeDTO.getLikeTip() == 1) {
                this.code = 2;
            } else {
                this.code = 1;
            }
        } else {
            System.out.println("save" + likeDTO);
            likeService.save(likeDTO);
            this.code = 0;
        }
        return ResponseEntity.ok(this.code);
    }

    @GetMapping("/like/search/bytipid/{tipId}")
    public ResponseEntity<?> selectLikeByTipId(@PathVariable int tipId) {

        List<Like> likeList = likeService.findByTipId(tipId);
        List<LikeDTO> likeDTOList = new ArrayList<>();
        LikeDTO like = new LikeDTO(0, 0);
        this.likeNumber = 0;
        if (likeList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (likeList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < likeList.size(); i++) {
                    LikeDTO likeDTO = likeList.get(i).toLikeDTO();
                    likeDTO.setCode(this.code);
                    if (likeList.get(i).getLikeTip() == 1) {
                        this.likeNumber = this.likeNumber + 1;
                        likeDTO.setLikenumber(this.likeNumber);
                    } else {
                        this.likeNumber = this.likeNumber;
                        likeDTO.setLikenumber(this.likeNumber);
                    }
                    likeDTO.setTipId(tipId);
                    likeDTO.setCityId(likeList.get(i).getCity().getCityId());
                    likeDTO.setSiteId(likeList.get(i).getSite().getSiteId());
                    likeDTO.setUserId(likeList.get(i).getUser().getUserId());
                    likeDTOList.add(likeDTO);
                }
            }
        }

        return ResponseEntity.ok(likeDTOList);
    }

    @GetMapping("/like/search/bysiteid/{siteId}")
    public ResponseEntity<?> selectLikeBySiteId(@PathVariable int siteId) {

        LikeDTO like = new LikeDTO(0, 0);
        List<Like> likeList = likeService.findBySiteId(siteId);
        List<LikeDTO> likeDTOList = new ArrayList<>();

        if (likeList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (likeList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < likeList.size(); i++) {
                    LikeDTO likeDTO = likeList.get(i).toLikeDTO();
                    likeDTO.setCode(this.code);
                    likeDTO.setUserId(likeList.get(i).getUser().getUserId());
                    likeDTO.setTipId(likeList.get(i).getTip().getTipId());
                    likeDTO.setCityId(likeList.get(i).getCity().getCityId());
                    likeDTO.setSiteId(siteId);
                    likeDTOList.add(likeDTO);
                }
            }
        }

        return ResponseEntity.ok(likeDTOList);
    }

    @GetMapping("/like/search/bycityid/{cityId}")
    public ResponseEntity<?> selectLikeByCityId(@PathVariable int cityId) {

        LikeDTO like = new LikeDTO(0, 0);
        List<Like> likeList = likeService.findByCityId(cityId);
        List<LikeDTO> likeDTOList = new ArrayList<>();

        if (likeList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (likeList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < likeList.size(); i++) {
                    LikeDTO likeDTO = likeList.get(i).toLikeDTO();
                    likeDTO.setCode(this.code);
                    likeDTO.setUserId(likeList.get(i).getUser().getUserId());
                    likeDTO.setTipId(likeList.get(i).getTip().getTipId());
                    likeDTO.setCityId(cityId);
                    likeDTO.setSiteId(likeList.get(i).getSite().getSiteId());
                    likeDTOList.add(likeDTO);
                }
            }
        }

        return ResponseEntity.ok(likeDTOList);
    }

    @GetMapping("/like/search/byuserid/{userId}")
    public ResponseEntity<?> selectLikeByUserId(@PathVariable int userId) {

        LikeDTO like = new LikeDTO(0, 0);
        List<Like> likeList = likeService.findByUserId(userId);
        List<LikeDTO> likeDTOList = new ArrayList<>();

        if (likeList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (likeList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < likeList.size(); i++) {
                    LikeDTO likeDTO = likeList.get(i).toLikeDTO();
                    likeDTO.setCode(this.code);
                    likeDTO.setUserId(userId);
                    likeDTO.setTipId(likeList.get(i).getTip().getTipId());
                    likeDTO.setCityId(likeList.get(i).getCity().getCityId());
                    likeDTO.setSiteId(likeList.get(i).getSite().getSiteId());
                    likeDTOList.add(likeDTO);
                }
            }
        }

        return ResponseEntity.ok(likeDTOList);
    }

    @GetMapping("/like/check/{tipId}/{userId}")
    public ResponseEntity<?> checkLike(@PathVariable int tipId, @PathVariable int userId) {
        List<Like> likeList1 = likeService.findByUserId(userId);
        this.code = 0;
        for (int i = 0; i < likeList1.size(); i++) {
            int id = likeList1.get(i).getTip().getTipId();
            if (id == tipId && likeList1.get(i).getLikeTip() == 1) {
                this.code = 1;
                break;
            }
        }
        return ResponseEntity.ok(this.code);
    }

}
