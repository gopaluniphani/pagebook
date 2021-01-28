package com.example.business.services.impl;

import com.example.business.dtos.AnalyticsDTO;
import com.example.business.models.Followers;
import com.example.business.repositories.FollowersRepository;
import com.example.business.services.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    FollowersRepository followersRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Followers save(Followers followers) {
        //todo : analytics
        /*new Thread(() -> {
            AnalyticsDTO analyticsDTO = new AnalyticsDTO();
            analyticsDTO.setChannel_id(2);
            analyticsDTO.setUserId( followers.getBusinessId());
            analyticsDTO.setAction("follow");
            restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
        }).start();*/
        return followersRepository.save(followers);
    }

    @Override
    public Optional<Followers> findById(String id) {
        return followersRepository.findById(id);
    }

    @Override
    public Optional<Followers> findByBusinessId(String businessId) {
        return followersRepository.findByBusinessId(businessId);
    }
}
