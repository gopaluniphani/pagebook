package com.example.business.services.impl;

import com.example.business.models.Posts;
import com.example.business.repositories.PostsRepository;
import com.example.business.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PostServiceImpl implements PostsService {

    @Autowired
    PostsRepository postsRepository;

    @Override
    public Posts save(Posts posts) {
        return postsRepository.save(posts);
    }

    @Override
    public Optional<Posts> findById(String id) {
        return postsRepository.findById(id);
    }

    @Override
    public Optional<Posts> findByBusinessId(String businessId) {
        return postsRepository.findByBusinessId(businessId);
    }
}
