package com.my.springboot.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class AdminResponse implements Serializable {
    private int userId;
    private String userName;
    private String userEmail;
    private String userAddress;
    private String userFavorite;
    private String admin;
}
