package com.my.springboot.travel.entity;

import com.my.springboot.travel.model.SiteDTO;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "site")
    private List<Tip> tip;

    public SiteDTO toSiteDTO(){
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setSiteId(this.getSiteId());
        siteDTO.setSiteName(this.getSiteName());
        siteDTO.setSiteAddress(this.getSiteAddress());
        siteDTO.setSitePhoto(this.getSitePhoto());
        return siteDTO;
    }


}
