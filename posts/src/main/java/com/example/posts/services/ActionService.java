package com.example.posts.services;

import com.example.posts.entity.Action;

import java.util.List;

public interface ActionService {

    int totalLikesByPostId(String postId);

    int totalDislikesByPostId(String postId);

    int totalWowEmojiByPostId(String postId);

    int totalSadEmojiByPostId(String postId);

    int performedActionByUserForPost(String postId, String userId);

    String findActionIdByPostIdAndUserId(String userId, String postId);

    Action save(Action action);

    List<String> getUserWhoLiked(String postId);

    List<String> getUserWhoDisliked(String postId);

    List<String> getUserOfWowEmoji(String postId);

    List<String> getUserOfSadEmoji(String postId);

    int getPerformedActionByUser(String postId, String userId);
}
