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
@RequestMapping("/pagebook/api/search/business/")
public class BusinessSearchController {

    @Autowired
    BusinessService businessService;

    @PostMapping(value="/")
    public BusinessProfile save(@RequestBody BusinessProfile profile){
        return businessService.save(profile);
    }

    @GetMapping(value="/findall")
    List<BusinessProfile> findAll(){
        return businessService.findAll();
    }

    @GetMapping(value="/{searchTerm}")
    public List<BusinessDTO> search(@PathVariable("searchTerm") String search){
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
