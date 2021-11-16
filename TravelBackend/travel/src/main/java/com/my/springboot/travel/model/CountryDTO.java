package com.my.springboot.travel.model;

import com.my.springboot.travel.entity.Site;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO implements Serializable {

    private int countryId;
    private String countryName;
    private String countryAddress;
    private String countryCulture;
    private String countryPhoto;
    private int code;

}
