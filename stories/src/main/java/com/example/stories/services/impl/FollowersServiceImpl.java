package com.example.business.services.impl;

import com.example.business.models.Followers;
import com.example.business.repositories.FollowersRepository;
import com.example.business.services.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    FollowersRepository followersRepository;

    @Override
    public Followers save(Followers followers) {
        return followersRepository.save(followers);
    }

    @Override
    public Optional<Followers> findById(String id) {
        return followersRepository.findById(id);
    }

    @Override
    public Optional<Followers> findByBusinessId(String businessId) {
        return followersRepository.findByBusinessId(businessId);
    }
}
