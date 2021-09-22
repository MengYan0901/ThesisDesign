package com.my.springboot.travel.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "site")
public class Site implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer siteId;

    @Column
    private String siteName;
    private String siteAddress;

    @Column
    private String sitePhoto;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
