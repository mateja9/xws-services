package xws.mediaservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.mediaservices.dto.CommentDTO;
import xws.mediaservices.model.Comment;
import xws.mediaservices.model.User;
import xws.mediaservices.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
public class  CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/media/post/comment/createComment")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("client");
        return new ResponseEntity<Comment>(commentService.createComment(commentDTO, sessionUser), HttpStatus.OK);
    }

    @GetMapping(value = "/media/post/comment/{postId}")
    public ResponseEntity<List<Comment>> getForPost(@PathVariable Long postId) {

        return new ResponseEntity<List<Comment>>(commentService.getCommentsForPost(postId), HttpStatus.OK);
    }
}
