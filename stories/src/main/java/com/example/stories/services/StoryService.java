package com.example.stories.services;

import com.example.stories.models.Story;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StoryService {
    Story save(Story story);
    Optional<Story> findById(String id);
    Optional<List<Story>> findByUserId(String id);
}
