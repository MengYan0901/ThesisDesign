package com.my.springboot.travel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private int commentId;
    private String tipComment;
    private int tipId;
    private int userId;
    private int code;

    public CommentDTO(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
