package com.example.business.repositories;

import com.example.business.models.Moderators;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorsRepository extends MongoRepository<Moderators, String> {
    Moderators findByBusinessId(String businessId);
}
