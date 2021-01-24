package com.example.stories.services.impl;

import com.example.stories.models.StoryFeed;
import com.example.stories.repositories.StoryFeedRepository;
import com.example.stories.services.StoryFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryFeedServiceImpl implements StoryFeedService {

    @Autowired
    StoryFeedRepository storyFeedRepository;

    @Override
    public StoryFeed save(StoryFeed storyFeed) {
        return storyFeedRepository.save(storyFeed);
    }

    @Override
    public Optional<StoryFeed> findById(String id) {
        return storyFeedRepository.findById(id);
    }

    @Override
    public Optional<StoryFeed> findByUserId(String userId) {
        return storyFeedRepository.findByUserId(userId);
    }
}
