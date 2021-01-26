package com.example.stories.services.impl;

import com.example.stories.models.Story;
import com.example.stories.repositories.StoryRepository;
import com.example.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Override
    public Story save(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public Optional<Story> findById(String id) {
        return storyRepository.findById(id);
    }

    @Override
    public Optional<List<Story>> findByUserId(String id) {
        return storyRepository.findByUserId(id);
    }

    @Override
    public void deleteById(String id) {
        storyRepository.deleteById(id);
    }
}
