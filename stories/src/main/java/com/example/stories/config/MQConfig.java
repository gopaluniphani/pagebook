package com.example.stories.config;

import org.springframework.amqp.core.*;
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
    public static final String SEND_USER_ID_QUEUE = "sendUserId";
    public static final String REQUEST_FRIENDS_LIST_QUEUE = "requestFriendsList";
    public static final String RECEIVE_FRIENDS_LIST_QUEUE = "receiveFriendsList";
    public static final String SEND_DELETE_REQUEST_QUEUE = "sendDeleteRequest";
    public static final String RECEIVE_DELETE_REQUEST_QUEUE = "receiveDeleteRequest";
    public static final String RECEIVE_FRIENDS_LIST_TO_DELETE_QUEUE = "receiveFriendsListToDelete";

    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    @Autowired
    private AmqpAdmin amqpAdmin;

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
    public Queue outgoingRequestQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        // The default exchange
        args.put("x-dead-letter-exchange", "");
        // Route to the incoming queue when the TTL occurs
        args.put("x-dead-letter-routing-key", REQUEST_FRIENDS_LIST_QUEUE);
        // TTL 5 seconds
        args.put("x-message-ttl", 0);
        return new Queue(SEND_USER_ID_QUEUE, false, false, false, args);
    }

    @Bean
    public Queue outgoingDeleteRequestQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        // The default exchange
        args.put("x-dead-letter-exchange", "");
        // Route to the incoming queue when the TTL occurs
        args.put("x-dead-letter-routing-key", RECEIVE_DELETE_REQUEST_QUEUE);
        // TTL 5 seconds
        args.put("x-message-ttl", 600000);
        return new Queue(SEND_DELETE_REQUEST_QUEUE, false, false, false, args);
    }

    @Bean
    public RabbitTemplate userIdSender() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(outgoingRequestQueue().getName());
        rabbitTemplate.setRoutingKey(outgoingRequestQueue().getName());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate deleteRequestSender() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(outgoingDeleteRequestQueue().getName());
        rabbitTemplate.setRoutingKey(outgoingDeleteRequestQueue().getName());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue incomingFriendsRequestQueue() {
        return new Queue(RECEIVE_FRIENDS_LIST_QUEUE);
    }

    @Bean
    public Queue incomingDeleteRequestQueue() {
        return new Queue(RECEIVE_FRIENDS_LIST_TO_DELETE_QUEUE);
    }
}
