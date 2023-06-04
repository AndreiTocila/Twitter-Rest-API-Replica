package ro.uaic.info.fiipractic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.fiipractic.DTO.PostDTO;
import ro.uaic.info.fiipractic.entity.PostEntity;
import ro.uaic.info.fiipractic.service.PostService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{user_id}")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<String> createPost(@PathVariable Integer user_id,@RequestBody PostDTO post)
    {
        postService.createPost(user_id,post);
        return new ResponseEntity<>("Post created!", HttpStatus.OK);
    }

    @PostMapping("/posts/{post_id}/repost")
    public ResponseEntity<String> repost (@PathVariable Integer user_id, @PathVariable Integer post_id)
    {
        postService.repost(user_id, post_id);
        return new ResponseEntity<>("Post reposted!", HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getPosts(@PathVariable Integer user_id, @RequestParam Optional<String> timestamp)
    {
        List<PostDTO> posts = postService.getPosts(user_id, timestamp);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostDTO>> getFeed (@PathVariable Integer user_id) {
        List<PostDTO> posts = postService.getFeed(user_id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/mentions")
    public ResponseEntity<List<PostDTO>> getMentions (@PathVariable Integer user_id) {
        List<PostDTO> posts = postService.getMentions(user_id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer user_id, @PathVariable Integer post_id)
    {
        postService.deletePost(user_id,post_id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }
}
