package com.my.springboot.travel.entity;

import com.my.springboot.travel.model.TipDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "tip")
public class Tip implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tipId;

    private String tipName;
    private String tipContent;
    private Integer tipMark;
    private String tipPhoto;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DAOUser user;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "tip")
    private List<Like> like;

    @OneToMany(mappedBy = "tip")
    private List<DAOComment> DAOComment;

    public TipDTO toTipDTO (){
        TipDTO tipDTO = new TipDTO();
        tipDTO.setTipId(this.getTipId());
        tipDTO.setTipName(this.getTipName());
        tipDTO.setTipContent(this.getTipContent());
        tipDTO.setTipMark(this.getTipMark());
        tipDTO.setTipPhoto(this.getTipPhoto());
        return tipDTO;
    }

}
