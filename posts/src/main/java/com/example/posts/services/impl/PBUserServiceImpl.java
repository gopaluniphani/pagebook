package com.example.posts.services.impl;

import com.example.posts.entity.User;
import com.example.posts.repositories.PBUserRepository;
import com.example.posts.services.PBUserService;
import dto.UpdateProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Kafka logic will be added later
@Service
public class PBUserServiceImpl implements PBUserService {

    @Autowired
    PBUserRepository PBUserRepository;

    @Override
    public String findUserNameByUserId(String userId) {
        return PBUserRepository.findUserNameByUserId(userId);
    }

    @Override
    public String findUserImgByUserId(String userId) {
        return PBUserRepository.findUserImgByUserId(userId);
    }

    @Override
    public void addUser(UpdateProfileDTO updateProfileDTO) {
        User user = User.builder()
                .userId(updateProfileDTO.getUserId())
                .userImgURL(updateProfileDTO.getUserImgURL())
                .userName(updateProfileDTO.getUserName())
                .build();
        PBUserRepository.save(user);
    }
}
