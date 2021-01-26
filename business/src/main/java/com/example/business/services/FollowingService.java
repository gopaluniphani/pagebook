package com.example.business.services;

import com.example.business.models.Following;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FollowingService {
    Following save(Following following);
    Optional<Following> findById(String id);
    Optional<Following> findByUserId(String userId);
}
