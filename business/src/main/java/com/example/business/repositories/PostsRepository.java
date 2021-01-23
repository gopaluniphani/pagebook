package com.example.business.repositories;

import com.example.business.models.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends MongoRepository<Posts, String> {
    Optional<Posts> findByBusinessId(String businessId);
}
