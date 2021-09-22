package com.my.springboot.travel.controller;

import com.my.springboot.travel.dao.UserDao;
import com.my.springboot.travel.entity.DAOUser;
import com.my.springboot.travel.model.UserDTO;
import com.my.springboot.travel.model.UserResponse;
import com.my.springboot.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController {

    private int code = 0;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> selectUserName(@PathVariable String userName){
        DAOUser user = userDao.findByUserName(userName);
        if (user == null) {
            this.code=0;
        } else {
            this.code=1;
        }
        return ResponseEntity.ok(new UserResponse(this.code));
    }
    @GetMapping("/user/list/{userName}")
    public ResponseEntity<?> showUserList(@PathVariable String userName){
        DAOUser user = userDao.findByUserName(userName);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/user/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

}
