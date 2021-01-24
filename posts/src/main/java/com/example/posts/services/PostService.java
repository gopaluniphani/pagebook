package com.example.posts.services;

import com.example.posts.entity.Post;
import com.example.posts.model.Response;

import java.util.List;

public interface PostService {

    Response addPost(Post post);

    Response findPostByUserId(String userId);

    Post findById(String postId);

    String findUserIdByPostId(String postId);
}
