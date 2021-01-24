package com.example.stories.mqlisteners;

import com.example.stories.config.MQConfig;
import com.example.stories.dto.GetFriendsListDTO;
import com.example.stories.models.StoryFeed;
import com.example.stories.services.StoryFeedService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MQListeners {

    @Autowired
    StoryFeedService storyFeedService;

    @RabbitListener(queues = MQConfig.RECEIVE_FRIENDS_LIST_QUEUE)
    public void recieveFriendsList(GetFriendsListDTO friendsListDTO) {
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
    }
}
