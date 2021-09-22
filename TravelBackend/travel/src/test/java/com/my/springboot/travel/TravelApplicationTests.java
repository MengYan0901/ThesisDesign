package com.my.springboot.travel;

import com.my.springboot.travel.dao.SiteDao;
import com.my.springboot.travel.dao.UserDao;
import com.my.springboot.travel.entity.Country;
import com.my.springboot.travel.entity.Site;
import com.my.springboot.travel.entity.DAOUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TravelApplicationTests {


    @Autowired
    SiteDao siteDao;

    @Test
    void testAddSite() {
        Site site = new Site();
        site.setSiteAddress("Budapest");
        site.setSiteName("Fisherman's Bastion");
        Country country = new Country();
        country.setCountryId(1);
        site.setCountry(country);
        siteDao.save(site);

    }

    @Autowired
    UserDao userDao;

    @Test
    void testAddUser() {
        DAOUser user = new DAOUser();
        user.setUserName("YaYa");
        user.setUserPassword("202399");
       userDao.save(user);

    }



}
