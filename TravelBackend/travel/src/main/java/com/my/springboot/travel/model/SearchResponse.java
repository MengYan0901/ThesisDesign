package com.my.springboot.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponse implements Serializable {

    private String SiteName;
    private String SiteAddress;
    private String SitePhoto;
    private String countryName;
    private String countryAddress;
    private String countryPhoto;

    private int code;
    public SearchResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
