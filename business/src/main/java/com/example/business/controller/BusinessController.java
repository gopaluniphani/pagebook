package com.example.business.controller;

import com.example.business.dto.Response;
import com.example.business.models.Business;
import com.example.business.models.Followers;
import com.example.business.models.Moderators;
import com.example.business.services.BusinessService;
import com.example.business.services.FollowersService;
import com.example.business.services.ModeratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/business/")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    ModeratorsService moderatorsService;

    @Autowired
    FollowersService followersService;


    @PostMapping(value = "/user/{userId}")
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

    @GetMapping(value = "/business/followers/{businessId}")
    public Response getFollowersOfBusiness(@PathVariable("businessId") String businessId) {
        Optional<Followers> followers = followersService.findByBusinessId(businessId);
        if(followers.isPresent()) {
            return new Response(true, "", followers.get().getFollowers());
        } else {
            return new Response(true, "", new ArrayList<>());
        }
    }

    @PostMapping(value = "/addmoderator/{businessId}/{moderatorId}")
    public Response addModerator(@PathVariable("businessId") String businessId, @PathVariable("moderatorId") String moderatorId) {
        Moderators moderators = moderatorsService.findByBusinessId(businessId).get();
        moderators.addModerator(moderatorId);
        return new Response(true, "", moderatorsService.save(moderators));
    }

    @PostMapping(value = "/addfollower/{businessId}/{followerId}")
    public Response addFollower(@PathVariable("businessId") String businessId, @PathVariable("followerId") String followerId) {
        Optional<Followers> followers = followersService.findByBusinessId(businessId);
        if(followers.isPresent()) {
            followers.get().addFollower(followerId);
            return new Response(true, "", followersService.save(followers.get()));
        } else {
            Followers newFollowers = new Followers();
            newFollowers.setBusinessId(businessId);
            newFollowers.addFollower(followerId);
            return new Response(true, "", followersService.save(newFollowers));
        }
    }
}
