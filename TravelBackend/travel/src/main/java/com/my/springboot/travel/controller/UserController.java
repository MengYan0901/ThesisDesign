package com.my.springboot.travel.controller;

import com.my.springboot.travel.dao.UserDao;
import com.my.springboot.travel.entity.DAOUser;
import com.my.springboot.travel.model.UserDTO;
import com.my.springboot.travel.model.UserResponse;
import com.my.springboot.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class UserController {

    private int code = 0;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userName}")
    public ResponseEntity<?> selectUserName(@PathVariable String userName) {
        DAOUser user = userDao.findByUserName(userName);
        if (user == null) {
            this.code = 0;
        } else {
            this.code = 1;
        }
        return ResponseEntity.ok(new UserResponse(this.code));
    }

    @GetMapping("/user/getuser/{userName}")
    public ResponseEntity<?> showUser(@PathVariable String userName) {
        DAOUser user = userService.findByUserName(userName);
        return ResponseEntity.ok(user.toUserDTO());
    }


    @PostMapping("/user/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/user/getuserlike/{userName}")
    public ResponseEntity<?> showUserLike(@PathVariable String userName) {
        List<DAOUser> userList = userService.findByUserNameLike(userName);
        List<UserDTO> userDTOList = new ArrayList<>();
        if (userList.size() == 0) {
            return ResponseEntity.ok(0);
        } else {
            if (userList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < userList.size(); i++) {
                    UserDTO userDTO = userList.get(i).toUserDTO();
                    userDTO.setCode(this.code);
                    userDTOList.add(userDTO);
                }
            }
        }
        return ResponseEntity.ok(userDTOList);
    }
}
