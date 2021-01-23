package com.example.business.services;

import com.example.business.models.ApprovedPosts;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ApprovedPostsService {
    Optional<ApprovedPosts> findById(String id);
    ApprovedPosts findByBusinessId(String businessId);
}
