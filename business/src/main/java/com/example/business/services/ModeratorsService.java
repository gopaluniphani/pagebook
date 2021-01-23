package com.example.business.services;

import com.example.business.models.Moderators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ModeratorsService {
    Moderators save(Moderators moderators);
    Optional<Moderators> findById(String id);
    Optional<Moderators> findByBusinessId(String businessId);
}
