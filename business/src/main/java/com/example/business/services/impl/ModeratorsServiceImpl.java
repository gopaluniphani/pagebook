package com.example.business.services.impl;

import com.example.business.models.Moderators;
import com.example.business.repositories.ModeratorsRepository;
import com.example.business.services.ModeratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeratorsServiceImpl implements ModeratorsService {

    @Autowired
    ModeratorsRepository moderatorsRepository;

    @Override
    public Optional<Moderators> findById(String id) {
        return moderatorsRepository.findById(id);
    }

    @Override
    public Moderators findByBusinessId(String businessId) {
        return moderatorsRepository.findByBusinessId(businessId);
    }
}
