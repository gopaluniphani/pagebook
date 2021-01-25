package com.example.posts.services.impl;

import com.example.posts.entity.Action;
import com.example.posts.repositories.ActionRepository;
import com.example.posts.repositories.PBUserRepository;
import com.example.posts.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionRepository actionRepository;
    @Autowired
    PBUserRepository pbUserRepository;

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

    @Override
    public List<String> getUserWhoLiked(String postId) {
        List<String> userIdList = actionRepository.getUserWhoLiked(postId);
        List<String> userNameList = new ArrayList<>();
        for( String userId : userIdList)
        {
            userNameList.add( pbUserRepository.findUserNameByUserId(userId));
        }
        return userNameList;
    }

    @Override
    public List<String> getUserWhoDisliked(String postId) {
        List<String> userIdList = actionRepository.getUserWhoDisliked(postId);
        List<String> userNameList = new ArrayList<>();
        for( String userId : userIdList)
        {
            userNameList.add( pbUserRepository.findUserNameByUserId(userId));
        }
        return userNameList;
    }

    @Override
    public List<String> getUserOfWowEmoji(String postId) {
        List<String> userIdList = actionRepository.getUserOfWowEmoji(postId);
        List<String> userNameList = new ArrayList<>();
        for( String userId : userIdList)
        {
            userNameList.add( pbUserRepository.findUserNameByUserId(userId));
        }
        return userNameList;
    }

    @Override
    public List<String> getUserOfSadEmoji(String postId) {
        List<String> userIdList = actionRepository.getUserOfSadEmoji(postId);
        List<String> userNameList = new ArrayList<>();
        for( String userId : userIdList)
        {
            userNameList.add( pbUserRepository.findUserNameByUserId(userId));
        }
        return userNameList;
    }
}
