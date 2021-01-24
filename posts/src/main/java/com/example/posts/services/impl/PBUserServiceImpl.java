package com.example.posts.services.impl;

import com.example.posts.repositories.PBUserRepository;
import com.example.posts.services.PBUserService;
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
}
