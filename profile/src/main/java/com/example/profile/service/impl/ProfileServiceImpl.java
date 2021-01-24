package com.example.profile.service.impl;

import com.example.profile.entity.Friend;
import com.example.profile.entity.Profile;
import com.example.profile.repository.ProfileRepository;
import com.example.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Profile save(Profile profile){
        return profileRepository.save(profile);
    }

    @Override
    public void addTotalFriend(String userId)
    {
        profileRepository.updateTotalFriend(userId, 1 + this.findTotalFriendById(userId));
    }
    @Override
    public int findTotalFriendById(String userId)
    {
        return profileRepository.findTotalFriendById(userId);
    }

    @Override
    public String getImgUrlById(String userId)
    {
        return profileRepository.getImgUrlById(userId);
    }

    @Override
    public String getProfileTypeById(String userId)
    {
        return profileRepository.getProfileTypeById(userId);
    }
    @Override
    public String getInterestById(String userId)
    {
        return profileRepository.getInterestById(userId);
    }

    @Override
    public String findUserNameById(String userId)
    {
        return profileRepository.findUserNameById(userId);
    }
    @Override
    public List<Profile> findAll()
    {
        Iterable<Profile> profileIterable = profileRepository.findAll();
        List<Profile> profileList= new ArrayList<>();
        profileIterable.forEach(profileList:: add);
        return profileList;
    }

    @Override
    public void updateImg(String userId, double imgUrl) {
        profileRepository.updateImg(userId, imgUrl);
        Profile profile = profileRepository.findById(userId).get();

    }





}
