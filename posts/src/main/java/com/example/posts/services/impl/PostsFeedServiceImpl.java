package com.example.posts.services.impl;

import com.example.posts.Constant;
import com.example.posts.entity.PostsFeed;
import com.example.posts.model.PostDTO;
import com.example.posts.repositories.PostsFeedRepository;
import com.example.posts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsFeedServiceImpl implements PostsFeedService {

    @Autowired
    PostService postService;
    @Autowired
    PBUserService pbUserService;
    @Autowired
    ActionService actionService;
    @Autowired
    CommentService commentService;
    @Autowired
    PostsFeedRepository postsFeedRepository;

    @Override
    public List<PostDTO> getPostsFeedByPage(String userId, int page) {

        List<PostDTO> postDTOS = new ArrayList<>();

        Pageable request = PageRequest.of(page - 1, Constant.PAGE_SIZE, Sort.Direction.DESC,"uploadTime");

        List<String> postIds = postsFeedRepository.findPostsFeedByPage(userId, request);
        for(String postId : postIds)
        {
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
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    @Override
    public void addToFeedsOfUser(PostsFeed postFeed) {
        postsFeedRepository.save(postFeed);
    }

    @Override
    public void save(PostsFeed postsFeed) {
        postsFeedRepository.save(postsFeed);
    }

}
