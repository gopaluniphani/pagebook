package com.example.search.controllers;

import com.example.search.dto.BusinessDTO;
import com.example.search.dto.UserDTO;
import com.example.search.models.BusinessProfile;
import com.example.search.models.UserProfile;
import com.example.search.services.BusinessService;
import com.example.search.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pagebook/api/search/business")
public class BusinessSearchController {

    @Autowired
    BusinessService businessService;

    @PostMapping(value="/")
    public BusinessProfile save(@RequestBody BusinessProfile profile){
        System.out.println("saving business Profile");
        return businessService.save(profile);
    }

    @PostMapping(value = "/{id}")
    public BusinessProfile updateProfile(@RequestBody BusinessProfile profile, @PathVariable("id") String id) {
        System.out.println("updating profile");
        profile.setId(id);
        return businessService.save(profile);
    }

    @GetMapping(value="/findall")
    List<BusinessProfile> findAll(){
        System.out.println("finding all business");
        return businessService.findAll();
    }

    @GetMapping(value="/{searchTerm}")
    public List<BusinessDTO> search(@PathVariable("searchTerm") String search){
        System.out.println("searching by term");
        List<BusinessProfile> profiles = businessService.processSearch(search);
        List<BusinessDTO> response = new ArrayList<>();
        for(BusinessProfile profile : profiles) {
            BusinessDTO dto = new BusinessDTO();
            dto.setId(profile.getId());
            dto.setImageUrl(profile.getImageUrl());
            dto.setName(profile.getName());
            response.add(dto);
        }
        return response;
    }
}
