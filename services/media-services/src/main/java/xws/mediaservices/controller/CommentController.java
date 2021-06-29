package xws.mediaservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.mediaservices.dto.CommentDTO;
import xws.mediaservices.model.Comment;
import xws.mediaservices.service.CommentService;

import java.util.List;

@RestController
public class  CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/media/comment/createComment")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<Comment>(commentService.createComment(commentDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/media/comment/{postId}")
    public ResponseEntity<List<Comment>> getForPost(@PathVariable Long postId) {

        return new ResponseEntity<List<Comment>>(commentService.getComentsForPost(postId), HttpStatus.OK);
    }
}
