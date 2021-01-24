package com.example.business.controller;

import com.example.business.dto.Response;
import com.example.business.services.BusinessService;
import com.example.business.services.ModeratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/business/")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    ModeratorsService moderatorsService;


    @PostMapping(value = "/{userId}")
    public Response createBusiness(@RequestBody Business business, @PathVariable("userId") String userId) {
        Business newBusiness = businessService.save(business);
        Moderators moderators = new Moderators();
        moderators.setBusinessId(newBusiness.getId());
        moderators.addModerator(userId);
        moderatorsService.save(moderators);
        return new Response(true, "", newBusiness);
    }

    @GetMapping(value = "/{id}")
    public Response retrieveBusinessDetails(@PathVariable("id") String businessId) {
        Optional<Business> business = businessService.findById(businessId);
        Response response = new Response();
        if(business.isPresent()) {
            response.setStatus(true);
            response.setBody(business.get());
        } else {
            response.setStatus(false);
            response.setErrorMessage("Cannot find any business with the given ID");
        }
        return  response;
    }
}
