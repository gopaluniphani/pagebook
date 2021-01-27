package com.example.posts.services.impl;

import com.example.posts.Constant;
import com.example.posts.entity.Post;
import com.example.posts.entity.PostsFeed;
import com.example.posts.model.FriendsResponse;
import com.example.posts.model.PostDTO;
import com.example.posts.model.Response;
import com.example.posts.repositories.PostRepository;
import com.example.posts.repositories.PostsFeedRepository;
import com.example.posts.services.ActionService;
import com.example.posts.services.CommentService;
import com.example.posts.services.PBUserService;
import com.example.posts.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PBUserService pbUserService;
    @Autowired
    CommentService commentService;
    @Autowired
    ActionService actionService;
    @Autowired
    PostsFeedRepository postsFeedRepository;

    @Override
    public Response addPost(Post post) {
        Response response;
        try {
            if(post.getProfileType().equals( Constant.PROFILE_TYPE_PRIVATE) ||
                post.getProfileType().equals( Constant.PROFILE_TYPE_PUBLIC) )
            {
                post.setApproved(true);
                new Thread(() -> {
                    List<String> friendsList = getFriendsList(post.getUserId());
                    System.out.println(friendsList);
                    PostsFeed postsFeed = PostsFeed.builder()
                            .postId(post.getPostId())
                            .build();
                    for(String friendId : friendsList)
                    {
                        postsFeed.setUserId(friendId);
                        postsFeedRepository.save(postsFeed);
                    }
                }).start();
            }
            System.out.println(post);
            Post addedPost = postRepository.save(post);
            response = Response.builder()
                    .status(true)
                    .body(addedPost)
                    .build();
        }
        catch (Exception e)
        {
            response = Response.builder()
                    .errorMessage(e.toString())
                    .build();
        }
        return response;
    }

    @Override
    public Response findPostByUserId(String userId) {
        Response response;
        List<Post> postList = postRepository.findPostByUserId(userId);
        List<PostDTO> postDTOS = new ArrayList<>();
        System.out.println(postList);
        for(Post post : postList)
        {
            String postId = post.getPostId();
            //System.out.println("postId : "+postId+" "+"userId : "+userId);
            PostDTO postDTO = PostDTO.builder()
                    .post( post)
                    .userImgURL( pbUserService.findUserImgByUserId(userId))
                    .userName( pbUserService.findUserNameByUserId(userId))
                    .totalComments( commentService.totalCommentsByPostId( postId))
                    .totalLikes( actionService.totalLikesByPostId(postId))
                    .totalDislikes( actionService.totalDislikesByPostId(postId))
                    .totalWowEmoji( actionService.totalWowEmojiByPostId(postId))
                    .totalSadEmoji( actionService.totalSadEmojiByPostId(postId))
                    .performedAction( actionService.performedActionByUserForPost(postId, userId))
                    .build();
            postDTOS.add(postDTO);
        }
        response = Response.builder()
                .status(true)
                .body(postDTOS)
                .build();
        return  response;
    }

    @Override
    public Post findById(String postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public String findUserIdByPostId(String postId) {
        return postRepository.findUserIdByPostId(postId);
    }

    @Override
    public List<Post> getUnapprovedPost(String businessId) {
        return postRepository.getUnApprovedPost(businessId);
    }

    @Override
    public List<Post> getBusinessPost(String businessId) {
        return postRepository.getBusinessPost(businessId);
    }

    @Override
    public String getProfileTypeByPostId(String postId) {
        return postRepository.getProfileTypeByPostId(postId);
    }

    @Override
    public Post approvePost(String postId) {
        Post post = postRepository.approvePost(postId);
        String businessId = postRepository.getBusinessIdByPostId(postId);
        //todo: fetch followers here
        new Thread(() -> {
            List<String> followerIds = getFollowersList(businessId);
            System.out.println(followerIds);
            PostsFeed postsFeed = PostsFeed.builder()
                    .postId(post.getPostId())
                    .build();
            for(String followerId : followerIds)
            {
                postsFeed.setUserId(followerId);
                postsFeedRepository.save(postsFeed);
            }
        }).start();
        return post;
    }

    @Override
    public List<String> getFriendsList(String userId) {
        System.out.println("got UserId in get friends list : "+ userId);
        FriendsResponse ids = restTemplate.getForObject("http://10.177.2.27:9002/pagebook/api/profile/getFriendsId" +
                "/"+userId, FriendsResponse.class);
        System.out.println(ids);
        return (List<String>) ids.getBody();
    }

    @Override
    public void unApprovePost(String postId) {
        postRepository.unApprovePost(postId);
    }

    //todo :
    List<String> getFollowersList(String businessId)
    {
        FriendsResponse ids = restTemplate.getForObject("http://10.177.1.178:8771/pagebook/api/business" +
                "/followers/"+businessId,FriendsResponse.class);
        return (List<String>) ids.getBody();
    }
}
