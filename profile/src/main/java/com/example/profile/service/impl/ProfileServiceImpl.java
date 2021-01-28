package com.example.profile.service.impl;

import com.example.profile.dto.AnalyticsDTO;
import com.example.profile.entity.Friend;
import com.example.profile.entity.Profile;
import com.example.profile.repository.ProfileRepository;
import com.example.profile.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UpdateProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    KafkaTemplate<String, UpdateProfileDTO> kafkaTemplate;

    @Override
    public Profile save(Profile profile){

        Profile profile1 = profileRepository.save(profile);
        UpdateProfileDTO updateProfileDTO = UpdateProfileDTO.builder()
                .userName(profile.getFirstName())
                .userId(profile.getUserId())
                .userImgURL(profile.getImgUrl())
                .build();

        new Thread(() -> {
            System.out.println("Created new thread " + updateProfileDTO);
            restTemplate.postForObject("http://10.177.1.117:8082/pagebook/api/post/addUser",updateProfileDTO, boolean.class);
        }).start();

        //todo : analytics
        /*System.out.println("Calling Analytics");
        new Thread(() -> {
            AnalyticsDTO analyticsDTO = new AnalyticsDTO();
            analyticsDTO.setChannel_id(2);
            analyticsDTO.setAction("register");
            analyticsDTO.setUserId( profile.getUserId());
            analyticsDTO.setCategory( profile.getInterest());

            restTemplate.postForObject("http://10.177.2.29:8760/analytics/internal/query", analyticsDTO, Void.class);
        }).start();*/

        return profile1;
    }

    @Override
    public void addTotalFriend(String userId)
    {
        profileRepository.updateTotalFriend(userId, 1 + this.findTotalFriendById(userId));
    }
    @Override
    public int findTotalFriendById(String userId)
    {
        return Integer.parseInt(profileRepository.findTotalFriendById(userId));
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
    public Profile getProfileByEmail(String email)
    {
        return profileRepository.getProfileByEmail(email);
    }

    @Override
    public Profile getProfileById(String userId)
    {
        //todo : analytics
        /*new Thread(() -> {
            AnalyticsDTO analyticsDTO = new AnalyticsDTO();
            analyticsDTO.setChannel_id(2);
            analyticsDTO.setUserId( userId);
            analyticsDTO.setAction("view profile");

            restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
        }).start();*/
        return profileRepository.getProfileById(userId);
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
    public void updateImg(String userId, String imgUrl) {
        profileRepository.updateImg(userId, imgUrl);
        Profile profile = profileRepository.findById(userId).get();
        UpdateProfileDTO updateProfileDTO = UpdateProfileDTO.builder()
                .userImgURL(imgUrl)
                .userId(userId)
                .userName(findUserNameById(userId))
                .build();

        new Thread(() -> {
            restTemplate.postForObject("http://10.177.1.117:8082/pagebook/api/post/addUser",updateProfileDTO, boolean.class);
        }).start();
    }





}
