package com.my.springboot.travel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my.springboot.travel.model.UserDTO;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Tip> tip;

    @OneToMany(mappedBy = "user")
    private List<Like> like;

    @OneToMany(mappedBy = "user")
    private List<DAOComment> comment;

    public UserDTO toUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(this.getUserId());
        userDTO.setUserName(this.getUserName());
        userDTO.setUserPassword(this.getUserPassword());
        userDTO.setUserEmail(this.getUserEmail());
        userDTO.setUserAddress(this.getUserAddress());
        userDTO.setUserFavorite(this.getUserFavorite());
        userDTO.setUserAdmin(this.getUserAdmin());
        return userDTO;
    }
}
