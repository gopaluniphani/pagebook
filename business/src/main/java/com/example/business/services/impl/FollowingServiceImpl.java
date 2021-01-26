package com.example.business.services.impl;

import com.example.business.models.Following;
import com.example.business.repositories.FollowingRepository;
import com.example.business.services.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    FollowingRepository followingRepository;

    @Override
    public Following save(Following following) {
        return followingRepository.save(following);
    }

    @Override
    public Optional<Following> findById(String id) {
        return followingRepository.findById(id);
    }

    @Override
    public Optional<Following> findByUserId(String userId) {
        return followingRepository.findByUserId(userId);
    }
}
