package com.my.springboot.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO implements Serializable {

    private int cityId;
    private String cityName;
    private String cityCulture;
    private String cityPhoto;
    private int code;
}
