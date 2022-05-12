package com.my.springboot.travel.dao;

import com.my.springboot.travel.entity.DAOComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<DAOComment,Integer> {
    List<DAOComment> findByTipTipId(Integer tipId);
    List<DAOComment> findByUserUserId(Integer userId);
    DAOComment findByCommentId(Integer commentId);
}
