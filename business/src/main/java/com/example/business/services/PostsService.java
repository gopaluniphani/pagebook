package com.example.business.services;

import com.example.business.models.Posts;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PostsService {
    Optional<Posts> findById(String id);
    Posts findByBusinessId(String businessId);
}
