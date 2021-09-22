package com.my.springboot.travel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity(name="user")
public class DAOUser implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "UserName can not be empty!")
    @Column
    private String userName;

    @Column
    @JsonIgnore
    @NotBlank(message = "UserPassword can not be empty!")
    private String userPassword;

    @Column
    @NotBlank(message = "UserPassword can not be empty!")
    @Email(message = "E-mail format is incorrect!")
    private String userEmail;

    @Column
    private String userAddress;
    private String userFavorite;
    private int userAdmin;


}
