package ro.uaic.info.fiipractic.service;

import java.util.Map;

public interface ReplyService {

    void createReply(Integer userId, Integer postId, Map<String,String> content);
}
