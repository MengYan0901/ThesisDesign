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
public class SiteDTO implements Serializable {

    private Integer siteId;
    private String siteName;
    private String siteAddress;
    private String sitePhoto;
    private int code = 0;

}
