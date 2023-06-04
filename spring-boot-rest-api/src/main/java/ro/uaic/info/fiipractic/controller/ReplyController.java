package ro.uaic.info.fiipractic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.fiipractic.service.ReplyService;

import java.util.Map;

@RestController
@RequestMapping("/users/{user_id}/posts/{post_id}/replies")
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping()
    public ResponseEntity<String> createReply(@PathVariable Integer user_id, @PathVariable Integer post_id, @RequestBody Map<String,String> content) {
        replyService.createReply(user_id, post_id, content);
        return new ResponseEntity<>("Reply created!", HttpStatus.OK);

    }
}
