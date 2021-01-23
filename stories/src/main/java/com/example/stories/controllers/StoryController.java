package com.example.stories.controllers;

import com.example.stories.models.Response;
import com.example.stories.models.Story;
import com.example.stories.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/story")
public class StoryController {

    @Autowired
    StoryService storyService;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping(value = "/")
    Response createNewStory(Story story) {
        return new Response(true, "", storyService.save(story));
    }

    @GetMapping(value = "/{id}")
    Response getStoryById(@PathVariable("id") String id) {
        Optional<Story> story = storyService.findById(id);
        if(story.isPresent()) return new Response(true, "", story.get());
        return new Response(false, "Can't find a story with given id", null);
    }

    @GetMapping(value = "/user/{userId}")
    Response getStoriesByUserId(@PathVariable("userId") String userId) {
        Optional<List<Story>> stories = storyService.findByUserId(userId);
        if(stories.isPresent()) return new Response(true, "", stories.get());
        return new Response(false, "Can't find a story with given user id", null);
    }

}
