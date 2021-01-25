package com.example.search.services;

import com.example.search.models.BusinessProfile;

import java.util.List;

public interface BusinessService {
    BusinessProfile save(BusinessProfile profile);
    List<BusinessProfile> findAll();
    BusinessProfile findById(String id);
    void deleteAll();
    List<BusinessProfile> processSearch(String query);
}
