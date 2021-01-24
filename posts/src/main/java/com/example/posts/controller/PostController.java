package com.example.posts.controller;

import com.example.posts.entity.Action;
import com.example.posts.entity.Comment;
import com.example.posts.entity.Post;
import com.example.posts.entity.PostsFeed;
import com.example.posts.model.Response;
import com.example.posts.services.*;
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
    @Autowired
    PostsFeedService postsFeedService;

    @PostMapping
    Response addPost(@RequestBody Post post)
    {
        return postService.addPost(post);
    }

    //todo: We can also add pagination here.
    @GetMapping("/getUsersPost/{userId}")
    Response getUsersPost(@PathVariable("userId") String userId)
    {
        return postService.findPostByUserId(userId);
    }

    //This method is created to test other API.
    @PostMapping("/addToFeedsOfUser")
    void addToFeedsOfUser(@RequestBody PostsFeed postFeed)
    {
        postsFeedService.addToFeedsOfUser(postFeed);
    }


    @GetMapping("/getFeedPosts/{userId}/{page}")
    Response gePostsFeed(@PathVariable("userId") String userId, @PathVariable("page") int page)
    {
        Response response = Response.builder()
                .status(true)
                .body(postsFeedService.getPostsFeedByPage(userId, page))
                .build();
        return  response;
    }

    @GetMapping("/getUnapprovedPost/{businessId}")
    Response getUnapprovedPost(@PathVariable("businessId")String businessId)
    {
        Response response = Response.builder()
                .body(postService.getUnapprovedPost(businessId))
                .status(true)
                .build();
        return response;
    }

    /*@GetMapping("/getApprovedPost/{businessId}")
    Response getApprovedPost(@PathVariable("businessId")String businessId)
    {
        Response response = Response.builder()
                .body(postService.getUnapprovedPost(businessId))
                .status(true)
                .build();
        return response;
    }*/

    @GetMapping("/getBusinessPost/{businessId}")
    Response getBusinessPost(@PathVariable("businessId")String businessId)
    {
        Response response = Response.builder()
                .body(postService.getBusinessPost(businessId))
                .status(true)
                .build();
        return response;
    }

    @PostMapping("/addComment")
    Response addComments(@RequestBody Comment comment)
    {
        Response response = Response.builder()
                .body( commentService.addComment( comment))
                .status(true)
                .build();
        return  response;
    }

    @PostMapping("/addAction")
    Response addAction(@RequestBody Action action)
    {
        Response response;
        String actionId = actionService.findActionIdByPostIdAndUserId(action.getUserId(), action.getPostId());
        if(actionId != null)
        {
            action.setActionId( actionId);
        }
        response = Response.builder()
                .body(actionService.save(action))
                .status(true)
                .build();
        return  response;
    }

}
