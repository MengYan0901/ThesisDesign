package com.my.springboot.travel.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class UserResponse implements Serializable {

    private int code;

}
