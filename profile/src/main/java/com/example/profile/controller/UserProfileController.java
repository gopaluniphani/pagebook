package com.example.profile.controller;

import com.example.profile.dto.FriendsDTO;
import com.example.profile.dto.RequestorDTO;
import com.example.profile.dto.Response;
import com.example.profile.entity.Friend;
import com.example.profile.entity.Profile;
import com.example.profile.entity.Request;
import com.example.profile.service.FriendService;
import com.example.profile.service.ProfileService;
import com.example.profile.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping ("/pagebook/api/profile")
public class UserProfileController {

    @Autowired
    ProfileService profileService;

    @Autowired
    FriendService friendService;

    @Autowired
    RequestService requestService;

    @PostMapping("/save")
    public Response addUser(@RequestBody Profile profile){
        Response response = new Response();

        response.setBody(profileService.save(profile));
        response.setStatus(true);
        return response;
    }
//    public Response save(@RequestBody Profile profile){
//        Response profileService.save(profile);
//    }

    @PostMapping("/addFriend")
    public Response save(@RequestBody Friend friend){
        Response response = new Response();
        response.setStatus(true);
        response.setBody(friendService.save(friend));

        return response;

    }

    @PostMapping("/addNewFriend")
    public Response addnewFriend(@RequestBody Friend friend){
        Response response = new Response();
        response.setStatus(true);

        Friend friend1=new Friend();
        friend1.setUserId(friend.getFriendId());
        friend1.setFriendId(friend.getUserId());

        List<Friend> friends = new ArrayList<>();
        friends.add(friend);
        friends.add(friendService.save(friend1));
        friends.add(friendService.save(friend));

        response.setBody(friends);

        return response;

    }

    @PostMapping("/addRequest")
    public Response save(@RequestBody Request request){
        Response response = new Response();
        response.setStatus(true);
        response.setBody(requestService.save(request));
        return response;
    }

    @PutMapping("/addTotalFriend/{userId}")
    public void addTotaFriend(@PathVariable("userId") String userId)
    {
        profileService.addTotalFriend(userId);
    }

    @GetMapping("/img/{userId}")
    public Response getImgUrlById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(profileService.getImgUrlById(userId));
        return response;
    }

    @GetMapping("/profileType/{userId}")
    public Response getProfileTypeById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(profileService.getProfileTypeById(userId));
        return response;
    }
    //to find profile using using email
    @GetMapping("/userProfile/{email}")
    public Response getProfileByEmail(@PathVariable("email") String email)
    {
        Response response = new Response();
        Profile profile = profileService.getProfileByEmail(email);
        if(profile != null) {
            response.setStatus(true);
            response.setBody(profile);
        } else {
            response.setStatus(false);
            response.setErrorMessage("no user exists with that email");
        }

        return response;
    }

    @GetMapping("/userProfileById/{userId}")
    public Response getProfileById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(profileService.getProfileById(userId));
        return response;
    }
    @GetMapping("/interest/{userId}")
    public Response getInterestById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(profileService.getInterestById(userId));
        return response;
    }
    @GetMapping("/friendName/{userId}")
    public Response findUserNameById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(profileService.findUserNameById(userId));
        return response;
    }

    @GetMapping("/findAll")
    public Response findAllProduct()
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(profileService.findAll());
        return response;
    }

    @PutMapping("/updateImg")
    public void updateImg(@RequestParam("userId")String userId, @RequestParam("imgUrl") String imgUrl)
    {
        profileService.updateImg(userId, imgUrl);
    }

//    @PutMapping("/updateUser")
//    public  void updateUser(@RequestBody Profile profile)
//    @GetMapping("/findFriendList")
//    public List<String> getNameFromId()
//    {
//        return profileService.getNameFromId();
//    }

//    @GetMapping("/findFriendList/{userId}")
//    public List<String> findFriendNameById(@PathVariable("userId") String userId)
//    {
//        return friendService.findFriendNameById(userId);
//    }

    @GetMapping("/findRequestorList/{userId}")
    public Response findRequestorNameById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setStatus(true);
        response.setBody(requestService.findRequestorNameById(userId));
        return response;
    }

    @DeleteMapping("/deleteRequest/{userId}")
    public void deleteRequestById(@PathVariable("userId") String userId)
    {
         requestService.deleteRequestById(userId);
    }

    //for showing requestor list in front-end with names and img.
    @GetMapping("/getRequestorList/{userId}")
    public Response getRequestorList(@PathVariable("userId") String userId)
    {
        Response response = new Response();

        List<String> requestorIds =  requestService.findRequestorId(userId);



        List<RequestorDTO> requestorDTOS = new ArrayList<>();

        for(String id : requestorIds)
        {
            RequestorDTO requestorDTO = new RequestorDTO();
            requestorDTO.setFirstName( profileService.findUserNameById( id));
            requestorDTO.setImgUrl( profileService.getImgUrlById( id));
            requestorDTO.setRequestorId(id);
            requestorDTOS.add( requestorDTO);
        }

        response.setBody(requestorDTOS);
        response.setStatus(true);
        return response;
    }

    //It is when showing list to user of his friend.
    @GetMapping("/findFriendList/{userId}")
    public Response findFriendNameById(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        List<String> friendIds =  friendService.findFriendId(userId);
        List<FriendsDTO> friendsDTOS = new ArrayList<>();

        for(String id: friendIds){
            FriendsDTO friendsDTO = new FriendsDTO();
            friendsDTO.setFirstName( profileService.findUserNameById(id));
            friendsDTO.setImgUrl( profileService.getImgUrlById( id));
            friendsDTO.setFriendId(id);
            friendsDTOS.add( friendsDTO);

        }

        response.setBody(friendsDTOS);
        response.setStatus(true);


        return response;
    }

    //It is when only Id required.
    @GetMapping("/getFriendsId/{userId}")
    public Response getFriendsId(@PathVariable("userId") String userId)
    {
        Response response = new Response();
        response.setBody(friendService.findFriendId(userId));
        response.setStatus(true);
        return response;
    }

    @Bean
    RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
