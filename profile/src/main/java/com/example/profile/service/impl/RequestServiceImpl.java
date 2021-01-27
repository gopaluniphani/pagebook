package com.example.profile.service.impl;

import com.example.profile.entity.Friend;
import com.example.profile.entity.Request;
import com.example.profile.repository.ProfileRepository;
import com.example.profile.repository.RequestRepository;
import com.example.profile.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Request save(Request request){
        return requestRepository.save(request);
    }


    @Override
    public List<String> findRequestorId(String userId)
    {
        Iterable<String> requestorIterable = requestRepository.findRequestorId(userId);
        List<String> requestorIdList= new ArrayList<>();
        requestorIterable.forEach(requestorIdList:: add);
        return requestorIdList;
    }

    @Override
    public List<String> getRequestorNameFromId(List<String> requestorIdList){
        List<String> requestorNameList= new ArrayList<>();
        for(String friendId :requestorIdList) {
            requestorNameList.add(profileRepository.findUserNameById(friendId));
        }
        return  requestorNameList;
    }
    @Override
    public List<String> findRequestorNameById(String userId)
    {
        List<String> idList=findRequestorId(userId);
        return getRequestorNameFromId(idList);
    }

    @Override
    public void deleteRequestById(String requestorId, String userId)
    {
        requestRepository.deleteRequestById(requestorId,userId);
    }
}
