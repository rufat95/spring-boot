package az.other.Other.services;

import az.other.Other.entities.Comment;
import az.other.Other.entities.Post;
import az.other.Other.entities.User;
import az.other.Other.repos.CommentRepository;
import az.other.Other.requests.CommentCreateRequest;
import az.other.Other.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userServices;
    private final PostService postServices;

    public CommentService(CommentRepository commentRepository,
                          UserService userServices,
                          PostService postServices) {
        this.commentRepository = commentRepository;
        this.userServices = userServices;
        this.postServices = postServices;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId,
                                                 Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }else
            return commentRepository.findAll();
    }

    public Comment getOneComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user = userServices.getOneUserById(newCommentRequest.getUserId());
        Post post = postServices.getOnePostById(newCommentRequest.getPostId());

        if (user != null && post != null){
            return null;
        }else {
            Comment commentToSave = new Comment();
            commentToSave.setId(newCommentRequest.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(newCommentRequest.getText());
            return commentRepository.save(commentToSave);
        }
    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
