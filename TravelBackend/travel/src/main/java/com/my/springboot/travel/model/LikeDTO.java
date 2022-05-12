package com.my.springboot.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO implements Serializable {

    private int likeId;
    private int likeTip;
    private int likeSite;
    private int likeCity;
    private int tipId;
    private int siteId;
    private int cityId;
    private int userId;
    private int code = 0;
    private int likenumber = 0;

    public LikeDTO(int code, int likenumber) {
        this.code = code;
        this.likenumber = likenumber;
    }

    public int getCode() {

        return this.code;
    }

    public int getLikeNumber() {
        return this.likenumber;
    }


}
