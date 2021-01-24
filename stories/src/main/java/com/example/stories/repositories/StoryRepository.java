package com.example.stories.repositories;

import com.example.stories.models.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryRepository extends MongoRepository<Story, String> {
    Optional<List<Story>> findByUserId(String id);
}
