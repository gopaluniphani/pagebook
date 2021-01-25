package com.example.search.services;

import com.example.search.models.UserProfile;

import java.util.List;

public interface UserService {
    UserProfile save(UserProfile profile);
    List<UserProfile> findAll();
    UserProfile findById(String id);
    void deleteAll();
    List<UserProfile> processSearch(String query);
}
