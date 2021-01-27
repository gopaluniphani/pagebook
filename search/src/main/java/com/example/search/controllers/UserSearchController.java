package com.example.search.controllers;

import com.example.search.dto.UserDTO;
import com.example.search.models.UserProfile;
import com.example.search.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pagebook/api/search/user")
public class UserSearchController {

    @Autowired
    UserService userService;

    @PostMapping(value="/")
    public UserProfile save(@RequestBody UserProfile profile){
        return userService.save(profile);
    }

    @PutMapping(value = "/{id}")
    public UserProfile updateProfile(@RequestBody UserProfile profile, @PathVariable("id") String id) {
        profile.setUserId(id);
        return userService.save(profile);
    }

    @GetMapping(value="/findall")
    List<UserProfile> findAll(){
        return userService.findAll();
    }

    @GetMapping(value="/{searchTerm}")
    public List<UserDTO> search(@PathVariable("searchTerm") String search){
        List<UserProfile> profiles = userService.processSearch(search);
        List<UserDTO> response = new ArrayList<>();
        for(UserProfile profile : profiles) {
            UserDTO dto = new UserDTO();
            dto.setId(profile.getUserId());
            dto.setImageUrl(profile.getImgUrl());
            dto.setName(profile.getFirstName() + " " + profile.getLastName());
            response.add(dto);
        }
        return response;
    }
}
