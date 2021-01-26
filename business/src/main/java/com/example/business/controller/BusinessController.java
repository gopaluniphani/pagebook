package com.example.business.controller;

import com.example.business.models.Business;
import com.example.business.models.Followers;
import com.example.business.models.Following;
import com.example.business.models.Moderators;
import com.example.business.services.BusinessService;
import com.example.business.services.FollowersService;
import com.example.business.services.FollowingService;
import com.example.business.services.ModeratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pagebook/api/business/")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    ModeratorsService moderatorsService;

    @Autowired
    FollowersService followersService;

    @Autowired
    FollowingService followingService;


    @PostMapping(value = "/moderator/{userId}")
    public Business createBusiness(@RequestBody Business business, @PathVariable("userId") String userId) {
        Business newBusiness = businessService.save(business);
        Moderators moderators = new Moderators();
        moderators.setBusinessId(newBusiness.getId());
        moderators.addModerator(userId);
        moderatorsService.save(moderators);
        return newBusiness;
    }

    @PutMapping(value = "/{id}")
    public Business updateBusiness(@RequestBody Business business, @PathVariable("id") String id) {
        business.setId(id);
        return businessService.save(business);
    }

    @GetMapping(value = "/{id}")
    public Business retrieveBusinessDetails(@PathVariable("id") String businessId) {
        return businessService.findById(businessId).get();
    }

    @GetMapping(value = "/followers/{businessId}")
    public Followers getFollowersOfBusiness(@PathVariable("businessId") String businessId) {
        return followersService.findByBusinessId(businessId).get();
    }

    @PostMapping(value = "/addmoderator/{businessId}/{moderatorId}")
    public Moderators addModerator(@PathVariable("businessId") String businessId, @PathVariable("moderatorId") String moderatorId) {
        Moderators moderators = moderatorsService.findByBusinessId(businessId).get();
        moderators.addModerator(moderatorId);
        return moderatorsService.save(moderators);
    }

    @PostMapping(value = "/addfollower/{businessId}/{followerId}")
    public Followers addFollower(@PathVariable("businessId") String businessId, @PathVariable("followerId") String followerId) {
        Optional<Followers> followers = followersService.findByBusinessId(businessId);
        Optional<Following> following = followingService.findByUserId(followerId);
        Following newFollowing;
        Followers newFollowers;

        if (!following.isPresent()) {
            newFollowing = new Following();
            newFollowing.setUserId(followerId);
        } else {
            newFollowing = following.get();
        }
        newFollowing.addFollowing(businessId);
        followingService.save(newFollowing);

        if (followers.isPresent()) {
            newFollowers = followers.get();
        } else {
            newFollowers = new Followers();
            newFollowers.setBusinessId(businessId);
        }
        newFollowers.addFollower(followerId);
        return followersService.save(newFollowers);
    }

    @GetMapping(value = "/following/{userId}")
    public List<String> getFollowingList(@PathVariable("userId") String userId) {
        Optional<Following> following = followingService.findByUserId(userId);
        if (following.isPresent()) {
            return following.get().getFollowing();
        } else {
            return new ArrayList<>();
        }
    }
}