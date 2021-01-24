package com.example.posts.services;

import com.example.posts.entity.Action;

public interface ActionService {

    int totalLikesByPostId(String postId);

    int totalDislikesByPostId(String postId);

    int totalWowEmojiByPostId(String postId);

    int totalSadEmojiByPostId(String postId);

    int performedActionByUserForPost(String postId, String userId);

    String findActionIdByPostIdAndUserId(String userId, String postId);

    Action save(Action action);

}
