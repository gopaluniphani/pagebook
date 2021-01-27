package com.example.profile.mqlisteners;

import com.example.profile.config.MQConfig;
import com.example.profile.dto.GetFriendsListDTO;
import com.example.profile.dto.RequestFriendsListDTO;
import com.example.profile.service.FriendService;
import com.example.profile.service.ProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MQListeners {
    @Autowired
    ProfileService profileService;

    @Autowired
    FriendService friendService;

    @Autowired
    private RabbitTemplate sendFriendList;

    @Autowired
    private RabbitTemplate sendFriendListToDelete;


    @RabbitListener(queues = MQConfig.SEND_FRIENDS_LIST_QUEUE)
    public void sendFriendList(RequestFriendsListDTO friendsListDTO) {
        GetFriendsListDTO getFriendsListDTO = new GetFriendsListDTO();
        getFriendsListDTO.setStoryId(friendsListDTO.getStoryId());
        getFriendsListDTO.setFriendsList(friendService.findFriendId(friendsListDTO.getUserId()));
        sendFriendList.convertAndSend(getFriendsListDTO);
    }

    @RabbitListener(queues = MQConfig.RECEIVE_DELETE_REQUEST_QUEUE)
    public void sendFriendListToDelete(RequestFriendsListDTO friendsListDTO) {
        GetFriendsListDTO getFriendsListDTO = new GetFriendsListDTO();
        getFriendsListDTO.setStoryId(friendsListDTO.getStoryId());
        getFriendsListDTO.setFriendsList(friendService.findFriendId(friendsListDTO.getUserId()));
        sendFriendListToDelete.convertAndSend(getFriendsListDTO);
    }
}
