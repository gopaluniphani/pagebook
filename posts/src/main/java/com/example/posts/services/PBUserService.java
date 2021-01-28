package com.example.posts.services;

import dto.UpdateProfileDTO;

public interface PBUserService {

    String findUserNameByUserId(String userId);

    String findUserImgByUserId(String userId);

    void addUser(UpdateProfileDTO updateProfileDTO);
}
