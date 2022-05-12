package com.my.springboot.travel.entity;

import com.my.springboot.travel.model.LikeDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name="favorite")
public class Like implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    private Integer likeSite;
    private Integer likeTip;
    private Integer likeCity;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "tip_id")
    private Tip tip;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DAOUser user;

    public LikeDTO toLikeDTO (){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLikeId(this.getLikeId());
        likeDTO.setLikeTip(this.getLikeTip());
        likeDTO.setLikeSite(this.getLikeSite());
        likeDTO.setLikeCity(this.getLikeCity());
        return likeDTO;
    }



}
