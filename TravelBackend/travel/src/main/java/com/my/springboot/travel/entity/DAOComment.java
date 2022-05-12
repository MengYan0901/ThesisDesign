package com.my.springboot.travel.entity;


import com.my.springboot.travel.model.CommentDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="comment")
public class DAOComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    private String tipComment;

    @ManyToOne
    @JoinColumn(name = "tip_id")
    private Tip tip;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DAOUser user;

    public CommentDTO toCommentDTO(){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(this.getCommentId());
        commentDTO.setTipComment(this.getTipComment());
        return commentDTO;
    }
}
