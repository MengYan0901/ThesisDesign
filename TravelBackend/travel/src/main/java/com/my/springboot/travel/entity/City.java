package com.my.springboot.travel.entity;

import com.my.springboot.travel.model.CityDTO;
import com.my.springboot.travel.model.CountryDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "city")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer cityId;

    private String cityName;

    private String cityCulture;

    private String cityPhoto;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Like> like;

    @OneToMany(mappedBy = "city")
    private List<Tip> tip;

    public CityDTO toCityDTO(){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setCityId(this.getCityId());
        cityDTO.setCityName(this.getCityName());
        cityDTO.setCityCulture(this.getCityCulture());
        cityDTO.setCityPhoto(this.getCityPhoto());
        return cityDTO;
    }
}
