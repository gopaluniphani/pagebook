package com.example.posts.controller;

import com.example.posts.entity.Post;
import com.example.posts.model.PostDTO;
import com.example.posts.model.Response;
import com.example.posts.services.ActionService;
import com.example.posts.services.CommentService;
import com.example.posts.services.PostService;
import com.example.posts.services.PBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagebook/api/post")
public class PostController {

    @Autowired
    ActionService actionService;
    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    PBUserService pbUserService;

    @PostMapping("/")
    Response addPost(@RequestBody Post post)
    {
        return postService.addPost(post);
    }

    //We can also add pagination here.
    @GetMapping("/getUsersPost/{userId}")
    Response getUsersPost(@PathVariable("userId") String userId)
    {
        return postService.findPostByUserId(userId);
    }



    @GetMapping("/getFeedPosts/{}")
    PostDTO getFeedPosts(@PathVariable("postId") String postId)
    {
        String userId = postService.findUserIdByPostId(postId);
        PostDTO postDTO = PostDTO.builder()
                .post( postService.findById(postId))
                .userImgURL( pbUserService.findUserImgByUserId(userId))
                .userName( pbUserService.findUserNameByUserId(userId))
                .totalComments( commentService.totalCommentsByPostId(postId))
                .totalLikes( actionService.totalLikesByPostId(postId))
                .totalDislikes( actionService.totalDislikesByPostId(postId))
                .totalWowEmoji( actionService.totalWowEmojiByPostId(postId))
                .totalSadEmoji( actionService.totalSadEmojiByPostId(postId))
                .performedAction( actionService.performedActionByUserForPost(postId, userId))
                .build();
        return postDTO;
    }

}
