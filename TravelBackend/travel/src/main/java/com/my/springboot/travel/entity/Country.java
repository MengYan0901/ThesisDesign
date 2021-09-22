package com.my.springboot.travel.entity;

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

    @Column
    private String countryName;
    private String countryAddress;
    private String countryCulture;
    private String countryPhoto;



}
