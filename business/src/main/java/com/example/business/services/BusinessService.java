package com.example.business.services;

import com.example.business.models.Business;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BusinessService {
    Business save(Business business);
    Optional<Business> findById(String id);
    List<Business> findAll();
    void deleteById(String id);
}
