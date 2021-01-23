package com.example.business.services;

import com.example.business.models.ApprovedPosts;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ApprovedPostsService {
    ApprovedPosts save(ApprovedPosts posts);
    Optional<ApprovedPosts> findById(String id);
    Optional<ApprovedPosts> findByBusinessId(String businessId);
}
