package com.my.springboot.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.springboot.travel.entity.DAOUser;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<DAOUser, Integer> {
    DAOUser findByUserName(String userName);
    List<DAOUser> findByUserNameLike(String userName);
}






