package com.example.posts.services.impl;

import com.example.posts.Constant;
import com.example.posts.entity.Post;
import com.example.posts.entity.PostsFeed;
import com.example.posts.model.*;
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

import java.time.LocalDateTime;
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
    public Post addPost(Post post) {
        //Response response;
        try {
            if(post.getProfileType().equals( Constant.PROFILE_TYPE_PRIVATE) ||
                    post.getProfileType().equals( Constant.PROFILE_TYPE_PUBLIC) )
            {
                post.setApproved(true);
            }
            System.out.println(post);
            Post addedPost = postRepository.save(post);
            new Thread(() -> {
                List<String> friendsList = getFriendsList(addedPost.getUserId());
                System.out.println(friendsList);

                for(String friendId : friendsList)
                {
                    PostsFeed postsFeed = PostsFeed.builder()
                            .postId(addedPost.getPostId())
                            .build();
                    postsFeed.setUserId(friendId);
                    System.out.println("added friend in User : "+ friendId);
                    postsFeedRepository.save(postsFeed);
                }
            }).start();
            //todo : for analytics
            /*new Thread(() -> {
                AnalyticsDTO analyticsDTO = AnalyticsDTO.builder()
                        .channel_id( 2)
                        .userId(post.getUserId())
                        .category( post.getPostCategory())
                        .type( post.getFileType())
                        .typeId( post.getPostId())
                        .action( "posting")
                        .time(LocalDateTime.now())
                        .build();
                restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
            }).start();*/
            return addedPost;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<PostDTO> findPostByUserId(String userId) {
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
            System.out.println(pbUserService.findUserNameByUserId("UserName: "+userId));
            postDTOS.add(postDTO);
        }
        /*response = Response.builder()
                .status(true)
                .body(postDTOS)
                .build();
        return  response;*/
        return postDTOS;
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
    public List<PostDTO> getUnapprovedPost(String businessId) {
        List<Post> postList = postRepository.getUnApprovedPost(businessId);
        List<PostDTO> postDTOS = new ArrayList<>();
        System.out.println(postList);
        for(Post post : postList)
        {
            String postId = post.getPostId();
            String userId = post.getUserId();
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
        return postDTOS;
    }

    @Override
    public List<PostDTO> getBusinessPost(String businessId) {
        List<Post> postList = postRepository.getBusinessPost(businessId);
        List<PostDTO> postDTOS = new ArrayList<>();
        System.out.println(postList);
        for(Post post : postList)
        {
            String postId = post.getPostId();
            String userId = post.getUserId();
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
        return postDTOS;
    }

    @Override
    public String getProfileTypeByPostId(String postId) {
        return postRepository.getProfileTypeByPostId(postId);
    }

    @Override
    public int approvePost(String postId) {
        postRepository.approvePost(postId);
        Post post = postRepository.findById(postId).get();
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
        //todo : for analytics
        /*new Thread(() -> {
            AnalyticsDTO analyticsDTO = AnalyticsDTO.builder()
                    .channel_id(2)
                    .userId(post.getUserId())
                    .category( post.getPostCategory())
                    .type( post.getFileType())
                    .typeId( post.getPostId())
                    .action( "posting")
                    .time(LocalDateTime.now())
                    .build();
            restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
        }).start();*/
        return 1;
    }

    @Override
    public List<String> getFriendsList(String userId) {
        System.out.println("got UserId in get friends list : "+ userId);
        //todo :
        List<String> ids = restTemplate.getForObject("http://10.177.2.29:8760/pagebook/api/profile/internal/getFriendsId" +
                "/"+userId, List.class);
        System.out.println(ids);
        return ids;
    }

    @Override
    public void unApprovePost(String postId) {
        postRepository.unApprovePost(postId);
    }

    List<String> getFollowersList(String businessId)
    {
        // TODO: register API in whitelist and make copy with /internal
        FollowersResponse followersResponse = restTemplate.getForObject("http://10.177.2.29:8760/pagebook/api/business/internal" +
                "/followers/"+businessId,FollowersResponse.class);
        System.out.println(followersResponse.getFollowers());
        return followersResponse.getFollowers();
    }
}
