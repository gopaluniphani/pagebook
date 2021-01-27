package com.example.profile.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MQConfig {
//    public static final String SEND_USER_ID_QUEUE = "sendUserId";
    public static final String RECEIVE_FRIENDS_LIST_QUEUE = "receiveFriendsList";
//    public static final String SEND_DELETE_REQUEST_QUEUE = "sendDeleteRequest";
    public static final String RECEIVE_DELETE_REQUEST_QUEUE = "receiveDeleteRequest";
    public static final String SEND_FRIENDS_LIST_QUEUE = "sendFriendsList";
    public static final String RECEIVE_FRIENDS_LIST_QUEUE_TO_DELETE_QUEUE = "receiveFriendsListToDelete";



    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    // Setting the annotation listeners to use the jackson2JsonMessageConverter
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        return factory;
    }

    // Standardize on a single objectMapper for all message queue items
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue sendFriendListQueue() {
        Map<String, Object> args = new HashMap<String, Object>();

        return new Queue(RECEIVE_FRIENDS_LIST_QUEUE, false, false, false, args);
    }

    @Bean
    public Queue sendFriendListToDeleteQueue() {
        Map<String, Object> args = new HashMap<String, Object>();

        return new Queue(RECEIVE_FRIENDS_LIST_QUEUE_TO_DELETE_QUEUE, false, false, false, args);
    }

    @Bean
    public RabbitTemplate sendFriendList() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(sendFriendListQueue().getName());
        rabbitTemplate.setRoutingKey(sendFriendListQueue().getName());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate sendFriendListToDelete() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(sendFriendListToDeleteQueue().getName());
        rabbitTemplate.setRoutingKey(sendFriendListToDeleteQueue().getName());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue incomingFriendsRequestQueue() {
        return new Queue(SEND_FRIENDS_LIST_QUEUE);
    }

    @Bean
    public Queue incomingDeleteRequestQueue() {
        return new Queue(RECEIVE_DELETE_REQUEST_QUEUE);
    }
}
