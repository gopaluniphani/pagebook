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
@CrossOrigin
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

    @GetMapping("/getComments")
    Response getComments(@RequestParam("parentCommentId") String parentCommentId,
                         @RequestParam("postId") String postId) {
        Response response = Response.builder()
                .body( commentService.getComments(parentCommentId, postId))
                .status(true)
                .build();
        return response;
    }

    @GetMapping("/getUserWhoLiked/{postId}")
    Response getUserWhoLiked(@PathVariable("postId") String postId)
    {
        Response response = Response.builder()
                .body( actionService.getUserWhoLiked( postId))
                .status(true)
                .build();
        return  response;
    }

    @GetMapping("/getUserWhoDisliked/{postId}")
    Response getUserWhoDisliked(@PathVariable("postId") String postId)
    {
        Response response = Response.builder()
                .body( actionService.getUserWhoDisliked( postId))
                .status(true)
                .build();
        return  response;
    }

    @GetMapping("/getUserOfWowEmoji/{postId}")
    Response getUserOfWowEmoji(@PathVariable("postId") String postId)
    {
        Response response = Response.builder()
                .body( actionService.getUserOfWowEmoji( postId))
                .status(true)
                .build();
        return  response;
    }

    @GetMapping("/getUserOfSadEmoji/{postId}")
    Response getUserOfSadEmoji(@PathVariable("postId") String postId)
    {
        Response response = Response.builder()
                .body( actionService.getUserOfSadEmoji( postId))
                .status(true)
                .build();
        return  response;
    }

    @PutMapping("/approvePost/{postId}")
    Response approvePost(@PathVariable("postId") String postId)
    {
        Response response = Response.builder()
                .status(true)
                .body( postService.approvePost( postId))
                .build();
        return response;
    }

    @PutMapping("/approveComment/{commentId}")
    Response approveComment(@PathVariable("commentId") String commentId)
    {
        Response response = Response.builder()
                .status(true)
                .body( commentService.approveComment( commentId))
                .build();
        return response;
    }
}
