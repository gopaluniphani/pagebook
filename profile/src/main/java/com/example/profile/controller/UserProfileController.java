package com.example.profile.controller;

import com.example.profile.config.MQConfig;
import com.example.profile.dto.*;
import com.example.profile.entity.Friend;
import com.example.profile.entity.Profile;
import com.example.profile.entity.Request;
import com.example.profile.service.FriendService;
import com.example.profile.service.ProfileService;
import com.example.profile.service.RequestService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Profile addUser(@RequestBody Profile profile){
        return profileService.save(profile);
    }
//    public Response save(@RequestBody Profile profile){
//        Response profileService.save(profile);
//    }

    @PostMapping("/addFriend")
    public Friend save(@RequestBody Friend friend){
        return friendService.save(friend);
    }

    @PostMapping("/addNewFriend")
    public List<Friend> addnewFriend(@RequestBody Friend friend){
        Friend friend1=new Friend();
        friend1.setUserId(friend.getFriendId());
        friend1.setFriendId(friend.getUserId());

        List<Friend> friends = new ArrayList<>();
        friends.add(friendService.save(friend1));
        friends.add(friendService.save(friend));
        profileService.addTotalFriend(friend.getUserId());
        profileService.addTotalFriend(friend.getFriendId());
        return friends;

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

    @GetMapping("/img/{userId}")
    public String getImgUrlById(@PathVariable("userId") String userId)
    {
        return profileService.getImgUrlById(userId);
    }

    @GetMapping("/profileType/{userId}")
    public String getProfileTypeById(@PathVariable("userId") String userId)
    {
        return profileService.getProfileTypeById(userId);
    }
    //to find profile using using email
    @GetMapping("/userProfile/{email}")
    public Profile getProfileByEmail(@PathVariable("email") String email)
    {
        return profileService.getProfileByEmail(email);
    }

    @GetMapping("/userProfileById/{userId}")
    public Profile getProfileById(@PathVariable("userId") String userId)
    {
        return profileService.getProfileById(userId);
    }
    @GetMapping("/interest/{userId}")
    public String getInterestById(@PathVariable("userId") String userId)
    {
        return profileService.getInterestById(userId);
    }
    @GetMapping("/friendName/{userId}")
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
    public void updateImg(@RequestParam("userId")String userId, @RequestParam("imgUrl") String imgUrl)
    {
        profileService.updateImg(userId, imgUrl);
    }

    @PutMapping("/updateUser/{userId}")
    public Profile updateUser(@RequestBody Profile profile, @PathVariable("userId") String userId) {
        profile.setUserId(userId);
        return profileService.save(profile);
    }
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
    public List<String> findRequestorNameById(@PathVariable("userId") String userId)
    {
        return requestService.findRequestorNameById(userId);
    }

    @DeleteMapping("deleteRequest")
    public void deleteRequestById(@RequestParam("requestorId")String requestorId, @RequestParam("userId") String userId){
        requestService.deleteRequestById(requestorId,userId);
    }

    //for showing requestor list in front-end with names and img.
    @GetMapping("/getRequestorList/{userId}")
    public List<RequestorDTO> getRequestorList(@PathVariable("userId") String userId)
    {

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

        return requestorDTOS;
    }

    //It is when showing list to user of his friend.
    @GetMapping("/findFriendList/{userId}")
    public List<FriendsDTO> findFriendNameById(@PathVariable("userId") String userId)
    {
        List<String> friendIds =  friendService.findFriendId(userId);
        List<FriendsDTO> friendsDTOS = new ArrayList<>();

        for(String id: friendIds){
            FriendsDTO friendsDTO = new FriendsDTO();
            friendsDTO.setFirstName( profileService.findUserNameById(id));
            friendsDTO.setImgUrl( profileService.getImgUrlById( id));
            friendsDTO.setFriendId(id);
            friendsDTOS.add( friendsDTO);

        }
        return friendsDTOS;
    }

    //It is when only Id required.
    @GetMapping("/getFriendsId/{userId}")
    public List<String> getFriendsId(@PathVariable("userId") String userId)
    {
        return friendService.findFriendId(userId);
    }

    @Bean
    RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

    @Autowired
    private RabbitTemplate sendFriendList;

    @Autowired
    private RabbitTemplate sendFriendListToDelete;


    @RabbitListener(queues = MQConfig.REQUEST_FRIENDS_LIST_QUEUE)
    public void sendFriendList(RequestFriendsListDTO friendsListDTO) {
        GetFriendsListDTO getFriendsListDTO = new GetFriendsListDTO();
        getFriendsListDTO.setStoryId(friendsListDTO.getStoryId());
        List<Friend> friends = friendService.findByUserId(friendsListDTO.getUserId());
        System.out.println("Inside sending friends list");
        for(Friend friend : friends) {
            System.out.println(friend.getFriendId());
            getFriendsListDTO.addFriend(friend.getFriendId());
        }
        sendFriendList.convertAndSend(getFriendsListDTO);
    }

    @RabbitListener(queues = MQConfig.RECEIVE_DELETE_REQUEST_QUEUE)
    public void sendFriendListToDelete(RequestFriendsListDTO friendsListDTO) {
        GetFriendsListDTO getFriendsListDTO = new GetFriendsListDTO();
        getFriendsListDTO.setStoryId(friendsListDTO.getStoryId());
        System.out.println("Inside sending friends list to delete");
        List<Friend> friends = friendService.findByUserId(friendsListDTO.getUserId());
        for(Friend friend : friends) {
            getFriendsListDTO.addFriend(friend.getFriendId());
        }
        sendFriendListToDelete.convertAndSend(getFriendsListDTO);
    }
}
