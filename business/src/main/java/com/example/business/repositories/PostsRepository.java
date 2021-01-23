package com.example.business.repositories;

import com.example.business.models.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends MongoRepository<Posts, String> {
    Posts findByBusinessId(String businessId);
}
