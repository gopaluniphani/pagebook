package com.example.posts.services;

import com.example.posts.entity.PostsFeed;
import com.example.posts.model.PostDTO;

import java.util.List;

public interface PostsFeedService {

    List<PostDTO> getPostsFeedByPage(String userId, int page);

    void addToFeedsOfUser(PostsFeed postFeed);
}
