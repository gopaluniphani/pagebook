package com.example.posts.controller;

import com.example.posts.entity.Action;
import com.example.posts.entity.Comment;
import com.example.posts.entity.Post;
import com.example.posts.entity.PostsFeed;
import com.example.posts.model.CommentsDTO;
import com.example.posts.model.PostDTO;
import com.example.posts.model.Response;
import com.example.posts.repositories.ActionRepository;
import com.example.posts.services.*;
import dto.UpdateProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
    @Autowired
    ActionRepository actionRepository;

    @PostMapping
    Post addPost(@RequestBody Post post)
    {
        //System.out.println("In add Post : "+post);
        return postService.addPost(post);
    }

    //todo: We can also add pagination here.
    @GetMapping("/getUsersPost/{userId}")
    List<PostDTO> getUsersPost(@PathVariable("userId") String userId)
    {
        System.out.println("In get Users Post : " + userId);
        return postService.findPostByUserId(userId);
    }

    //This method is created to test other API.
    @PostMapping("/addToFeedsOfUser")
    void addToFeedsOfUser(@RequestBody PostsFeed postFeed)
    {
        postsFeedService.addToFeedsOfUser(postFeed);
    }


    @GetMapping("/getFeedPosts/{userId}/{page}")
    List<PostDTO> getFeedPosts(@PathVariable("userId") String userId, @PathVariable("page") int page)
    {
        System.out.println("in get feeds post : "+ userId+" "+page);
        /*Response response = Response.builder()
                .status(true)
                .body(postsFeedService.getPostsFeedByPage(userId, page))
                .build();
        return  response;*/
        return postsFeedService.getPostsFeedByPage(userId, page);
    }

    @GetMapping("/getUnapprovedPost/{businessId}")
    List<PostDTO> getUnapprovedPost(@PathVariable("businessId")String businessId)
    {
        System.out.println("In get Unapproved post : "+businessId);
        /*Response response = Response.builder()
                .body(postService.getUnapprovedPost(businessId))
                .status(true)
                .build();*/
        return postService.getUnapprovedPost(businessId);
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
    List<PostDTO> getBusinessPost(@PathVariable("businessId")String businessId)
    {
        System.out.println("In get Business Post" + businessId);
        /*Response response = Response.builder()
                .body(postService.getBusinessPost(businessId))
                .status(true)
                .build();*/
        return postService.getBusinessPost(businessId);
    }

    @PostMapping("/addComment")
    Comment addComments(@RequestBody Comment comment)
    {
        System.out.println("In add comment : " + comment);
        /*Response response = Response.builder()
                .body( commentService.addComment( comment))
                .status(true)
                .build();*/
        return  commentService.addComment( comment);
    }

    @PostMapping("/addAction")
    Action addAction(@RequestBody Action action)
    {
        System.out.println("In add Action : " + action);
        //Response response;
        String actionId = actionService.findActionIdByPostIdAndUserId(action.getUserId(), action.getPostId());
        if(actionId != null)
        {
            action.setActionId( actionId);
        }
        System.out.println(action);
        /*response = Response.builder()
                .body(actionService.save(action))
                .status(true)
                .build();*/
        return  actionService.save(action);
    }

    @GetMapping("/getComments")
    List<CommentsDTO> getComments(@RequestParam("parentCommentId") String parentCommentId,
                                  @RequestParam("postId") String postId) {
        System.out.println("In get Comments : " + parentCommentId+" "+postId);
        /*Response response = Response.builder()
                .body( commentService.getComments(parentCommentId, postId))
                .status(true)
                .build();*/
        System.out.println(commentService.getComments(parentCommentId, postId));
        return commentService.getComments(parentCommentId, postId);
    }

    @GetMapping("/getUserWhoLiked/{postId}")
    List<String> getUserWhoLiked(@PathVariable("postId") String postId)
    {
        /*Response response = Response.builder()
                .body( actionService.getUserWhoLiked( postId))
                .status(true)
                .build();*/
        return  actionService.getUserWhoLiked( postId);
    }

    @GetMapping("/getUserWhoDisliked/{postId}")
    List<String> getUserWhoDisliked(@PathVariable("postId") String postId)
    {
        /*Response response = Response.builder()
                .body( actionService.getUserWhoDisliked( postId))
                .status(true)
                .build();*/
        return  actionService.getUserWhoDisliked( postId);
    }

    @GetMapping("/getUserOfWowEmoji/{postId}")
    List<String> getUserOfWowEmoji(@PathVariable("postId") String postId)
    {
        /*Response response = Response.builder()
                .body( actionService.getUserOfWowEmoji( postId))
                .status(true)
                .build();*/
        return  actionService.getUserOfWowEmoji( postId);
    }

    @GetMapping("/getUserOfSadEmoji/{postId}")
    List<String> getUserOfSadEmoji(@PathVariable("postId") String postId)
    {
        /*Response response = Response.builder()
                .body( actionService.getUserOfSadEmoji( postId))
                .status(true)
                .build();*/
        return  actionService.getUserOfSadEmoji( postId);
    }

    @PutMapping("/approvePost/{postId}")
    int approvePost(@PathVariable("postId") String postId)
    {
        System.out.println("In approve Post : " + postId);
        /*Response response = Response.builder()
                .status(true)
                .body( postService.approvePost( postId))
                .build();*/
        return postService.approvePost( postId);
    }

    @PutMapping("/approveComment/{commentId}")
    int approveComment(@PathVariable("commentId") String commentId)
    {
        System.out.println("In approve Comment : " + commentId);
        /*Response response = Response.builder()
                .status(true)
                .body( commentService.approveComment( commentId))
                .build();*/
        return commentService.approveComment( commentId);
    }

    @GetMapping("/fetchFriendIds/{userId}")
    public void findFriendsIds(@PathVariable("userId") String userId)
    {
        System.out.println("Got Id : "+userId);
        postService.getFriendsList(userId);
    }

    @DeleteMapping("/unApprovePost/{postId}")
    public void unApprovePost(@PathVariable("postId") String postId)
    {
        postService.unApprovePost(postId);
        /*Response response = Response.builder()
                .status(true)
                .build();
        return response;*/
    }

    @DeleteMapping("/unApproveComment/{commentId}")
    public void unApproveComment(@PathVariable("commentId") String commentId)
    {
        commentService.unApproveComment(commentId);
        /*Response response = Response.builder()
                .status(true)
                .build();
        return response;*/
    }

    //It is created for testing
    @GetMapping("/getPerformedAction/{postId}/{userId}")
    public int getAction(@PathVariable("postId") String postId, @PathVariable("userId") String userID)
    {
        return actionService.performedActionByUserForPost(postId, userID);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody UpdateProfileDTO updateProfileDTO)
    {
        System.out.println("Got : "+ updateProfileDTO);
        pbUserService.addUser(updateProfileDTO);
        return true;
    }

    @PostMapping("/internal/addUser")
    public boolean addUserWithRestTemp(@RequestBody UpdateProfileDTO updateProfileDTO)
    {
        System.out.println("In internal Got: "+ updateProfileDTO);
        pbUserService.addUser(updateProfileDTO);
        return true;
    }

    @Bean
    RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
