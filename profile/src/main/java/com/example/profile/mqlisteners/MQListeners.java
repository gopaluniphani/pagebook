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


}
