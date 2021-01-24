package com.example.profile.controller;

import com.example.profile.entity.Friend;
import com.example.profile.entity.Profile;
import com.example.profile.entity.Request;
import com.example.profile.service.FriendService;
import com.example.profile.service.ProfileService;
import com.example.profile.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/pagebook/profile")
public class UserProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    FriendService friendService;

    @Autowired
    RequestService requestService;

    @PostMapping
    public Profile save(@RequestBody Profile profile){
        return profileService.save(profile);
    }

    @PostMapping("/addFriend")
    public Friend save(@RequestBody Friend friend){
        return friendService.save(friend);
    }

    @PostMapping("/addRequest")
    public Request save(@RequestBody Request request){
        return requestService.save(request);
    }

    @PutMapping("/addTotalFriend/{userId}")
    public void addTotaFriend(@PathVariable("userId") String userId)
    {
        profileService.addTotalFriend(userId);
    }

    @GetMapping("img/{userId}")
    public String getImgUrlById(@PathVariable("userId") String userId)
    {
        return profileService.getImgUrlById(userId);
    }
    @GetMapping("profileType/{userId}")
    public String getProfileTypeById(@PathVariable("userId") String userId)
    {
        return profileService.getProfileTypeById(userId);
    }
    @GetMapping("interest/{userId}")
    public String getInterestById(@PathVariable("userId") String userId)
    {
        return profileService.getInterestById(userId);
    }
    @GetMapping("friendName/{userId}")
    public String findUserNameById(@PathVariable("userId") String userId)
    {
        return profileService.findUserNameById(userId);
    }

    @GetMapping("/findAll")
    public List<Profile> findAllProduct()
    {
        return profileService.findAll();
    }

    @PutMapping("/updateImg")
    public void updateImg(@RequestParam("userId")String userId, @RequestParam("imgUrl") double imgUrl)
    {
        profileService.updateImg(userId, imgUrl);
    }

//    @GetMapping("/findFriendList")
//    public List<String> getNameFromId()
//    {
//        return profileService.getNameFromId();
//    }

    @GetMapping("/findFriendList/{userId}")
    public List<String> findFriendNameById(@PathVariable("userId") String userId)
    {
        return friendService.findFriendNameById(userId);
    }

    @GetMapping("/findRequestorList/{userId}")
    public List<String> findRequestorNameById(@PathVariable("userId") String userId)
    {
        return requestService.findRequestorNameById(userId);
    }

    @DeleteMapping("deleterequest/{userID}")
    public void deleteRequestById(@PathVariable("userId") String userId)
    {
         requestService.deleteRequestById(userId);
    }

}
