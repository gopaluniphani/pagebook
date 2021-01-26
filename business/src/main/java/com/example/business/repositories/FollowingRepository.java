package com.example.business.repositories;

import com.example.business.models.Following;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowingRepository extends MongoRepository<Following, String> {
    Optional<Following> findByUserId(String userId);
}
