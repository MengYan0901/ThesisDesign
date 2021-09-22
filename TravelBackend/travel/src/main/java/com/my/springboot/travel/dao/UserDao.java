package com.my.springboot.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.springboot.travel.entity.DAOUser;

@Repository
public interface UserDao extends JpaRepository<DAOUser, Integer> {

    DAOUser findByUserName(String userName);

}






