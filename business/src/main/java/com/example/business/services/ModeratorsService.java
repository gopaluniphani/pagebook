package com.example.business.services;

import com.example.business.models.Moderators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ModeratorsService {
    Optional<Moderators> findById(String id);
    Moderators findByBusinessId(String businessId);
}
