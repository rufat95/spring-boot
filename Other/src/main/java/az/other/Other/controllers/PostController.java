package az.other.Other.controllers;

import az.other.Other.entities.Post;
import az.other.Other.requests.PostCreateRequest;
import az.other.Other.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return this.postService.createOnePost(newPostRequest);
    }

    @GetMapping("/{post_id}")
    public Post getOnePost(@PathVariable Long post_id){
        return this.postService.getOnePostById(post_id);
    }
}
