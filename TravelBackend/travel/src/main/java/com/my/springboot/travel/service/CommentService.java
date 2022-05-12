package com.my.springboot.travel.service;

import com.my.springboot.travel.dao.CommentDao;
import com.my.springboot.travel.dao.TipDao;
import com.my.springboot.travel.dao.UserDao;
import com.my.springboot.travel.entity.DAOComment;
import com.my.springboot.travel.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TipDao tipDao;

    @Autowired
    private UserDao userDao;

    public List<DAOComment> findAll() {

        return commentDao.findAll();
    }

    public List<DAOComment> findByTipId(int tipId) {

        return commentDao.findByTipTipId(tipId);
    }

    public List<DAOComment> findByUserId(int userId) {

        return commentDao.findByUserUserId(userId);
    }

    public void deleteCommentById(int commentId) {

        commentDao.deleteById(commentId);
    }

    public DAOComment save(CommentDTO comment) {
        DAOComment newComment = new DAOComment();
        newComment.setTipComment(comment.getTipComment());
        newComment.setTip(tipDao.findByTipId(comment.getTipId()));
        newComment.setUser(userDao.findByUserId(comment.getUserId()));
        return commentDao.save(newComment);
    }

    public CommentDTO updateComment(CommentDTO comment) {
        DAOComment newComment = commentDao.findByCommentId(comment.getCommentId());
        newComment.setTipComment(comment.getTipComment());
        newComment.setTip(tipDao.findByTipId(comment.getTipId()));
        newComment.setUser(userDao.findByUserId(comment.getUserId()));
        commentDao.save(newComment);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(newComment.getCommentId());
        commentDTO.setTipComment(newComment.getTipComment());
        commentDTO.setUserId(newComment.getUser().getUserId());
        commentDTO.setTipId(newComment.getTip().getTipId());
        return commentDTO;
    }
}
