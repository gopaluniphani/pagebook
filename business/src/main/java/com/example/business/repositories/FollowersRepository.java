package com.example.business.repositories;

import com.example.business.models.Followers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowersRepository extends MongoRepository<Followers, String> {
    Optional<Followers> findByBusinessId(String businessId);
}
