package com.my.springboot.travel.controller;

import com.my.springboot.travel.entity.DAOUser;
import com.my.springboot.travel.model.UserDTO;
import com.my.springboot.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/list")
    public ResponseEntity<?> showUserList(){
        List<DAOUser> user = userService.findAll();
        return ResponseEntity.ok(user);

    }

    @PostMapping("/admin/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping(value = "/admin/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok(1);
    }

}
