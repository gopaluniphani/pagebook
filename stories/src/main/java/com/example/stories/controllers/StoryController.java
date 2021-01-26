package com.example.stories.controllers;

import com.example.stories.dto.RequestFriendsListDTO;
import com.example.stories.models.Story;
import com.example.stories.services.StoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/story")
public class StoryController {

    @Autowired
    StoryService storyService;

//    @Autowired
//    KafkaTemplate<String, RequestFriendsListDTO> requestFriendsList;

    @Autowired
    private RabbitTemplate userIdSender;

    @Autowired
    private RabbitTemplate deleteRequestSender;

    @PostMapping(value = "/")
    Story createNewStory(@RequestBody Story story) {
        Story newStory = storyService.save(story);
        RequestFriendsListDTO requestFriendsListDTO = new RequestFriendsListDTO();
        requestFriendsListDTO.setUserId(newStory.getUserId());
        requestFriendsListDTO.setStoryId(newStory.getId());

        userIdSender.convertAndSend(requestFriendsListDTO);
        deleteRequestSender.convertAndSend(requestFriendsListDTO);

        return newStory;
    }

    @GetMapping(value = "/{id}")
    Story getStoryById(@PathVariable("id") String id) {
        return storyService.findById(id).get();
    }

    @GetMapping(value = "/user/{userId}")
    List<Story> getStoriesByUserId(@PathVariable("userId") String userId) {
        Optional<List<Story>> stories = storyService.findByUserId(userId);
        if(stories.isPresent()) return stories.get();
        return new ArrayList<>();
    }

}
