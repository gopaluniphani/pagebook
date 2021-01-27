package com.example.profile.service;

import com.example.profile.entity.Friend;

import java.util.List;

public interface FriendService {


    Friend save(Friend friend);
//    List<String> findFriendName(String friendId);
    List<String> findFriendId(String userId);
    List<String>getNameFromId(List<String> friendIdList);
    List<Friend> findByUserId(String userId);
    List<String> findFriendNameById(String userId);
//    List<String> findFriendNameById(String userId);
}
