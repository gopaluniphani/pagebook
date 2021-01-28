package com.example.posts.services;

import com.example.posts.entity.Post;
import com.example.posts.model.PostDTO;
import com.example.posts.model.Response;

import java.util.List;

public interface PostService {

    Post addPost(Post post);

    List<PostDTO> findPostByUserId(String userId);

    Post findById(String postId);

    String findUserIdByPostId(String postId);

    List<PostDTO> getUnapprovedPost(String businessId);

    List<PostDTO> getBusinessPost(String businessId);

    String getProfileTypeByPostId(String postId);

    int approvePost(String postId);

    List<String> getFriendsList(String userId);

    void unApprovePost(String postId);
}
