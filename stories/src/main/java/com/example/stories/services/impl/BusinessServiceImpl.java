package com.example.business.services.impl;

import com.example.business.models.Business;
import com.example.business.repositories.BusinessRepository;
import com.example.business.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    @Override
    public Business save(Business business) {
        return businessRepository.save(business);
    }

    @Override
    public Optional<Business> findById(String id) {
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
