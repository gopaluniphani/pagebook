package com.example.business.services.impl;

import com.example.business.dtos.AnalyticsDTO;
import com.example.business.models.Business;
import com.example.business.repositories.BusinessRepository;
import com.example.business.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Business save(Business business) {
        //todo : analytics
        /*new Thread(() -> {
            AnalyticsDTO analyticsDTO = new AnalyticsDTO();
            analyticsDTO.setChannel_id(2);
            analyticsDTO.setUserId(business.getId());
            analyticsDTO.setCategory( business.getCategory());
            analyticsDTO.setAction("register");
            analyticsDTO.setTime(LocalDateTime.now());

            restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
        }).start();*/
        return businessRepository.save(business);
    }

    @Override
    public Optional<Business> findById(String id) {
        //todo : analytics
        /*new Thread(() -> {
            AnalyticsDTO analyticsDTO = new AnalyticsDTO();
            analyticsDTO.setChannel_id(2);
            analyticsDTO.setUserId(id);
            analyticsDTO.setAction("view profile");

            restTemplate.postForObject("http://10.177.2.29:8760/analytics/query", analyticsDTO, Void.class);
        }).start();*/
        return businessRepository.findById(id);
    }

    @Override
    public List<Business> findAll() {
        return businessRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        businessRepository.deleteById(id);
    }
}
