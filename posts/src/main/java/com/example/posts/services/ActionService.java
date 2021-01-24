package com.example.posts.services;

public interface ActionService {

    int totalLikesByPostId(String postId);

    int totalDislikesByPostId(String postId);

    int totalWowEmojiByPostId(String postId);

    int totalSadEmojiByPostId(String postId);

    int performedActionByUserForPost(String postId, String userId);
}
