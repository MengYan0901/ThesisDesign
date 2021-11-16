package com.my.springboot.travel.model;

import com.my.springboot.travel.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipDTO implements Serializable {

    private int tipId;
    private String tipContent;
    private String tipName;
    private String tipMark;
    private String tipPhoto;
    private int userId;
    private int countryId;
    private int siteId;
    private int code = 0;

    public TipDTO(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
