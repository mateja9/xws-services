package xws.mediaservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.mediaservices.dto.CommentDTO;
import xws.mediaservices.model.Comment;
import xws.mediaservices.model.Post;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.CommentRepository;
import xws.mediaservices.repository.PostRepository;
import xws.mediaservices.repository.UserRepository;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired CommentRepository commentRepository;

    public Comment findById(Long id) {
        return null;
    }

    @Override
    public Comment createComment(CommentDTO commentDTO) {
        Comment newComment = new Comment();

        newComment.setAutorId(commentDTO.getAutorId());
        newComment.setPostId(commentDTO.getPostId());
        newComment.setContent(commentDTO.getContent());
        newComment.setUsername(commentDTO.getUsername());

        newComment = commentRepository.save(newComment);

        return newComment;
    }

    @Override
    public List<Comment> getCommentsForPost(Long id) {

        return commentRepository.findAllByPostId(id);
    }
}
