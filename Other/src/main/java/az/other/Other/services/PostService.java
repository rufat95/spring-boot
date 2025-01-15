package az.other.Other.services;

import az.other.Other.entities.Post;
import az.other.Other.entities.User;
import az.other.Other.repos.PostRepository;
import az.other.Other.requests.PostCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getOnePostById(Long post_id) {
        return postRepository.findById(post_id).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUser_id());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setPost_id(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }
}
