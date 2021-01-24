package com.example.stories.controllers;

import com.example.stories.dto.ImageDTO;
import com.example.stories.dto.RequestFriendsListDTO;
import com.example.stories.dto.Response;
import com.example.stories.models.Story;
import com.example.stories.services.StoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

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
    Response createNewStory(@RequestBody Story story) {
        Story newStory = storyService.save(story);
        RequestFriendsListDTO requestFriendsListDTO = new RequestFriendsListDTO();
        requestFriendsListDTO.setUserId(newStory.getUserId());
        requestFriendsListDTO.setStoryId(newStory.getId());

        userIdSender.convertAndSend(requestFriendsListDTO);
        deleteRequestSender.convertAndSend(requestFriendsListDTO);

//        requestFriendsList.send("requestFriendsList", "requestFriendsKey", requestFriendsListDTO);
        return new Response(true, "", newStory);
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
