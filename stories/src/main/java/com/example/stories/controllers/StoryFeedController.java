package com.example.stories.controllers;

import com.example.stories.models.Response;
import com.example.stories.models.StoryFeed;
import com.example.stories.dto.StoryFeedDTO;
import com.example.stories.services.StoryFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/storyfeed/")
public class StoryFeedController {

    @Autowired
    StoryFeedService storyFeedService;

    @PostMapping("/add/{userId}")
    Response addStoryToFeed(@PathVariable("userId") String userId, @RequestBody StoryFeedDTO storyFeedDTO) {
        Optional<StoryFeed> storyFeed = storyFeedService.findByUserId(userId);
        StoryFeed newStoryFeed;
        if(!storyFeed.isPresent()) {
            newStoryFeed = new StoryFeed();
            newStoryFeed.setUserId(userId);
        } else {
            newStoryFeed = storyFeed.get();
        }
        newStoryFeed.addStory(storyFeedDTO.getStoryId());
        return new Response(true, "", storyFeedService.save(newStoryFeed));
    }
}
