package com.my.springboot.travel.controller;

import com.my.springboot.travel.entity.DAOComment;
import com.my.springboot.travel.entity.Tip;
import com.my.springboot.travel.model.CommentDTO;
import com.my.springboot.travel.model.TipDTO;
import com.my.springboot.travel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CommentController {

    private int code = 0;

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/list")
    public ResponseEntity<?> showCommentList(){
        List<DAOComment> commentList = commentService.findAll();
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++) {
            CommentDTO commentDTO = commentList.get(i).toCommentDTO();
            commentDTO.setUserId(commentList.get(i).getUser().getUserId());
            commentDTO.setTipId(commentList.get(i).getTip().getTipId());
            commentDTOList.add(commentDTO);
        }
        return ResponseEntity.ok(commentDTOList);
    }

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.save(commentDTO));
    }

    @PostMapping("/comment/update")
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.updateComment(commentDTO));
    }

    @DeleteMapping("/comment/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId){
        commentService.deleteCommentById(commentId);
        return ResponseEntity.ok(1);
    }


    @GetMapping("/comment/search/bytipid/{tipId}")
    public ResponseEntity<?> searchCommentByTipId(@PathVariable int tipId){

        CommentDTO comment = new CommentDTO(0);
        List<DAOComment> commentList = commentService.findByTipId(tipId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if(commentList.size()==0){
            return ResponseEntity.ok(0);
        }else{
            if (commentList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < commentList.size(); i++) {
                    CommentDTO commentDTO = commentList.get(i).toCommentDTO();
                    commentDTO.setCode(this.code);
                    commentDTO.setTipId(tipId);
                    commentDTO.setUserId(commentList.get(i).getUser().getUserId());
                    commentDTOList.add(commentDTO);
                }
            }
        }
        return ResponseEntity.ok(commentDTOList);
    }

    @GetMapping("/comment/search/byuserid/{userId}")
    public ResponseEntity<?> searchCommentByUserId(@PathVariable int userId){

        CommentDTO comment = new CommentDTO(0);
        List<DAOComment> commentList = commentService.findByUserId(userId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if(commentList.size()==0){
            return ResponseEntity.ok(0);
        }else{
            if (commentList.size() > 0) {
                this.code = 1;
                for (int i = 0; i < commentList.size(); i++) {
                    CommentDTO commentDTO = commentList.get(i).toCommentDTO();
                    commentDTO.setCode(this.code);
                    commentDTO.setUserId(userId);
                    commentDTO.setTipId(commentList.get(i).getTip().getTipId());
                    commentDTOList.add(commentDTO);
                }
            }
        }
        return ResponseEntity.ok(commentDTOList);
    }
}
