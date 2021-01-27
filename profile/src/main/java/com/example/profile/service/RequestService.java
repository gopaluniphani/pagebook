package com.example.profile.service;

import com.example.profile.entity.Request;

import java.util.List;

public interface RequestService {

    Request save(Request request);
    List<String> findRequestorId(String userId);
    List<String>getRequestorNameFromId(List<String> friendIdList);
    List<String> findRequestorNameById(String userId);
    void deleteRequestById(String userId);
}
