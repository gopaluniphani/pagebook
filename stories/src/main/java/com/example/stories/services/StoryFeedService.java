package com.example.stories.services;

import com.example.stories.models.StoryFeed;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface StoryFeedService {
    StoryFeed save(StoryFeed storyFeed);
    Optional<StoryFeed> findById(String id);
    Optional<StoryFeed> findByUserId(String userId);
}
