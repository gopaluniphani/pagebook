package com.example.posts.services.impl;

import com.example.posts.entity.Action;
import com.example.posts.repositories.ActionRepository;
import com.example.posts.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionRepository actionRepository;

    @Override
    public int totalLikesByPostId(String postId) {
        return actionRepository.totalLikesByPostId(postId);
    }

    @Override
    public int totalDislikesByPostId(String postId) {
        return actionRepository.totalDislikesByPostId(postId);
    }

    @Override
    public int totalWowEmojiByPostId(String postId) {
        return actionRepository.totalWowEmojiByPostId(postId);
    }

    @Override
    public int totalSadEmojiByPostId(String postId) {
        return actionRepository.totalSadEmojiByPostId(postId);
    }

    @Override
    public int performedActionByUserForPost(String postId, String userId) {
        return actionRepository.performedActionByUserForPost(postId, userId);
    }

    @Override
    public String findActionIdByPostIdAndUserId(String userId, String postId) {
        return actionRepository.findActionIdByPostIdAndUserId(userId, postId);
    }

    @Override
    public Action save(Action action) {
        return actionRepository.save(action);
    }
}
