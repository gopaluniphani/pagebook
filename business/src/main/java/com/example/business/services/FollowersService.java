package com.example.business.services;

import com.example.business.models.Followers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FollowersService {
    Followers save(Followers followers);
    Optional<Followers> findById(String id);
    Optional<Followers> findByBusinessId(String businessId);
}
