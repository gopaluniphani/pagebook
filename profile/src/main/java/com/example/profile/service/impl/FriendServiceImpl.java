package com.example.profile.service.impl;

import com.example.profile.entity.Friend;
import com.example.profile.repository.FriendRepository;
import com.example.profile.repository.ProfileRepository;
import com.example.profile.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Friend save(Friend friend){
        return friendRepository.save(friend);
    }

//    @Override
//    public List<Friend> findFriendName(String friendId)
//    {
//        Iterable<String> friendIterable = friendRepository.findFriendName(friendId);
//        List<Friend> friendList= new ArrayList<>();
//        friendIterable.forEach(friendList::add);
//        return friendList;
//    }

    @Override
    public List<String> findFriendId(String userId)
    {
        Iterable<String> friendIterable = friendRepository.findFriendId(userId);
        List<String> friendIdList= new ArrayList<>();
        friendIterable.forEach(friendIdList:: add);
        return friendIdList;
    }

    @Override
    public List<String> getNameFromId(List<String> friendIdList){
        List<String> friendNameList= new ArrayList<>();
        for(String friendId :friendIdList) {
            friendNameList.add(profileRepository.findUserNameById(friendId));
        }
        return  friendNameList;
    }
    @Override
    public List<String> findFriendNameById(String userId)
    {
        List<String> idList=findFriendId(userId);
        return getNameFromId(idList);
    }

    @Override
    public List<Friend> findByUserId(String userId) {
        return friendRepository.findByUserId(userId);
    }
}
