package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.UserDao;
import com.my.springboot.travel.entity.DAOUser;
import com.my.springboot.travel.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public DAOUser updateUser(UserDTO user) {
        DAOUser newUser = userDao.findByUserName(user.getUserName());
        newUser.setUserName(user.getUserName());
        newUser.setUserEmail(user.getUserEmail());
        newUser.setUserAddress(user.getUserAddress());
        newUser.setUserFavorite(user.getUserFavorite());
        newUser.setUserAdmin(user.getUserAdmin());
        return userDao.save(newUser);
    }

    public void deleteUser(int userId) {
         userDao.deleteById(userId);
    }

    public List<DAOUser> findAll() {
        return userDao.findAll();
    }

}
