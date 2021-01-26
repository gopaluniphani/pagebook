package com.example.stories.controllers;

import com.example.stories.config.MQConfig;
import com.example.stories.dto.GetFriendsListDTO;
import com.example.stories.models.Story;
import com.example.stories.models.StoryFeed;
import com.example.stories.dto.StoryFeedDTO;
import com.example.stories.services.StoryFeedService;
import com.example.stories.services.StoryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/story/feed")
public class StoryFeedController {

    @Autowired
    StoryFeedService storyFeedService;

    @Autowired
    StoryService storyService;

    @GetMapping("/user/{userId}")
    StoryFeedDTO getStoryFeedForUser(@PathVariable("userId") String userId) {
        Optional<StoryFeed> feed = storyFeedService.findByUserId(userId);
        if(!feed.isPresent()) {
            return new StoryFeedDTO();
        }
        List<String> idsList = feed.get().getStories();
        StoryFeedDTO response = new StoryFeedDTO();
        for(String id : idsList) {
            Optional<Story> story = storyService.findById(id);
            if(story.isPresent())
                response.addToStories(story.get());
        }
        return response;
    }

    @RabbitListener(queues = MQConfig.RECEIVE_FRIENDS_LIST_QUEUE)
    public void recieveFriendsList(GetFriendsListDTO friendsListDTO) {
        System.out.println("inside mq listener receive friends list");
        List<String> friends = friendsListDTO.getFriendsList();
        for(String friend : friends) {
            StoryFeed newFeed;
            Optional<StoryFeed> feed = storyFeedService.findByUserId(friend);
            if(!feed.isPresent()) {
                newFeed = new StoryFeed();
                newFeed.setUserId(friend);
            } else {
                newFeed = feed.get();
            }
            newFeed.addStory(friendsListDTO.getStoryId());
            storyFeedService.save(newFeed);
        }
    }

    @RabbitListener(queues = MQConfig.RECEIVE_FRIENDS_LIST_TO_DELETE_QUEUE)
    public void deleteStoryInFeed(GetFriendsListDTO friendsListDTO) {
        System.out.println("inside mq listener receive friends list to delete from the feed");
        List<String> friends = friendsListDTO.getFriendsList();
        for(String friend : friends) {
            StoryFeed newFeed;
            Optional<StoryFeed> feed = storyFeedService.findByUserId(friend);
            if(!feed.isPresent()) {
                newFeed = new StoryFeed();
                newFeed.setUserId(friend);
            } else {
                newFeed = feed.get();
            }
            newFeed.removeStory(friendsListDTO.getStoryId());
            storyFeedService.save(newFeed);
        }
        storyService.deleteById(friendsListDTO.getStoryId());
    }
}
