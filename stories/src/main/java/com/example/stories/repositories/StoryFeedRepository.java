package com.example.stories.repositories;

import com.example.stories.models.StoryFeed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryFeedRepository extends MongoRepository<StoryFeed, String> {
    Optional<StoryFeed> findByUserId(String userId);
}
