package com.example.business.services;

import com.example.business.models.Followers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FollowersService {
    Optional<Followers> findById(String id);
    Followers findByBusinessId(String businessId);
}
