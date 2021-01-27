package com.example.profile.service;

import com.example.profile.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile save(Profile profile);
    void addTotalFriend(String userId);
    int findTotalFriendById(String userId);
    String getImgUrlById(String userId);
    String getProfileTypeById(String userId);
    String getInterestById(String userId);
    List<Profile> findAll();
    void updateImg(String userId, String imgUrl);
    String findUserNameById(String userId);
    Profile getProfileByEmail(String email);
    Profile getProfileById(String userId);
}
