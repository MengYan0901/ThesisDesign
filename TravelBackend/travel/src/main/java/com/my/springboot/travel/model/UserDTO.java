package com.my.springboot.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userAddress;
    private String userFavorite;
    private int userAdmin;
    private int code = 0;
}