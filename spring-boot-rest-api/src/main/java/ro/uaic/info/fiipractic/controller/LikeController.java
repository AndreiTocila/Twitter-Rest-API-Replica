package ro.uaic.info.fiipractic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.fiipractic.service.LikeService;

@RestController
@RequestMapping("users/{user_id}/posts/{post_id}/likes")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping()
    public ResponseEntity<String> likePost(@PathVariable Integer user_id, @PathVariable Integer post_id) {
        likeService.likePost(user_id, post_id);
        return new ResponseEntity<>("Post liked!", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteLike(@PathVariable Integer user_id, @PathVariable Integer post_id) {
        likeService.deleteLike(user_id, post_id);
        return new ResponseEntity<>("Like deleted!", HttpStatus.OK);
    }
}
