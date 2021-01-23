package com.example.business.repositories;

import com.example.business.models.ApprovedPosts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApprovedPostsRepository extends MongoRepository<ApprovedPosts, String> {
    Optional<ApprovedPosts> findByBusinessId(String businessId);
}
