package com.my.springboot.travel.entity;

import com.my.springboot.travel.model.CountryDTO;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer countryId;

    @OneToMany(mappedBy = "country")
    private List<Site> site;

    @OneToMany(mappedBy = "country")
    private List<Tip> tip;

    @Column
    private String countryName;
    private String countryAddress;
    private String countryCulture;
    private String countryPhoto;

    public CountryDTO toCountryDTO(){
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryId(this.getCountryId());
        countryDTO.setCountryName(this.getCountryName());
        countryDTO.setCountryAddress(this.getCountryAddress());
        countryDTO.setCountryCulture(this.getCountryCulture());
        countryDTO.setCountryPhoto(this.getCountryPhoto());
        return countryDTO;
    }

}
