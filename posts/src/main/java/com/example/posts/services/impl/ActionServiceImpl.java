package com.example.posts.services.impl;

import com.example.posts.entity.Action;
import com.example.posts.model.AnalyticsDTO;
import com.example.posts.repositories.ActionRepository;
import com.example.posts.repositories.PBUserRepository;
import com.example.posts.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    ActionRepository actionRepository;
    @Autowired
    PBUserRepository pbUserRepository;
    @Autowired
    RestTemplate restTemplate;

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
        Optional<Integer> response = actionRepository.performedActionByUserForPost(postId, userId);
        if(response.isPresent()) return response.get();
        else return 0;
    }

    @Override
    public String findActionIdByPostIdAndUserId(String userId, String postId) {
        return actionRepository.findActionIdByPostIdAndUserId(userId, postId);
    }

    @Override
    public Action save(Action action) {
        //todo : analytics
        /*new Thread(() -> {
            int performedAction = 0;
            if(action.getActionType() == 3 || action.getActionType() == 1)
            {
                performedAction = 1;
            }
            else if(action.getActionType() == 4 || action.getActionType() == 2)
            {
                performedAction = 2;
            }
            AnalyticsDTO analyticsDTO;
            if ( performedAction == 1)
            {
                analyticsDTO = AnalyticsDTO.builder()
                        .channel_id( 2)
                        .userId( action.getUserId())
                        .typeId( action.getPostId())
                        .action("like")
                        .build();
            }
            else {
                analyticsDTO = AnalyticsDTO.builder()
                        .channel_id( 2)
                        .userId( action.getUserId())
                        .typeId( action.getPostId())
                        .action("dislike")
                        .build();
            }
            restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
        }).start();*/
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

    @Override
    public int getPerformedActionByUser(String postId, String userId) {
        return actionRepository.getPerformedActionByUserForPost(postId,userId);
    }
}
