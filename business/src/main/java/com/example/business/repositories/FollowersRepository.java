package com.example.business.repositories;

import com.example.business.models.Followers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowersRepository extends MongoRepository<Followers, String> {
    Followers findByBusinessId(String businessId);
}
