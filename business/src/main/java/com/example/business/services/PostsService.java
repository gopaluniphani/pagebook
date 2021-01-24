package com.example.business.services;

import com.example.business.models.Posts;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PostsService {
    Posts save(Posts posts);
    Optional<Posts> findById(String id);
    Optional<Posts> findByBusinessId(String businessId);
}
