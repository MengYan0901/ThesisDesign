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

    public UserDTO updateUser(UserDTO user) {
        DAOUser newUser = userDao.findByUserName(user.getUserName());
        newUser.setUserName(user.getUserName());
        newUser.setUserEmail(user.getUserEmail());
        newUser.setUserAddress(user.getUserAddress());
        newUser.setUserFavorite(user.getUserFavorite());
        newUser.setUserAdmin(user.getUserAdmin());
        userDao.save(newUser);
        return newUser.toUserDTO();
    }

    public void deleteUser(int userId) {
         userDao.deleteById(userId);
    }

    public List<DAOUser> findAll() {
        return userDao.findAll();
    }

    public DAOUser findByUserName(String userName){
        return userDao.findByUserName(userName);
    }

    public List<DAOUser> findByUserNameLike(String userName){
        return userDao.findByUserNameLike("%"+userName+"%");
    }

}
