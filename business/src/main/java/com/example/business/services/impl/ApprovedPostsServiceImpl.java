package com.example.business.services.impl;

import com.example.business.models.ApprovedPosts;
import com.example.business.repositories.ApprovedPostsRepository;
import com.example.business.services.ApprovedPostsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ApprovedPostsServiceImpl implements ApprovedPostsService {

    @Autowired
    ApprovedPostsRepository approvedPostsRepository;

    @Override
    public Optional<ApprovedPosts> findById(String id) {
        return approvedPostsRepository.findById(id);
    }

    @Override
    public ApprovedPosts findByBusinessId(String businessId) {
        return approvedPostsRepository.findByBusinessId(businessId);
    }
}
