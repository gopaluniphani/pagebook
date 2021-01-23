package com.example.business.controller;

import com.example.business.models.*;
import com.example.business.services.BusinessService;
import com.example.business.services.ApprovedPostsService;
import com.example.business.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pagebook/api/business/")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    ApprovedPostsService approvedPostsService;

    @Autowired
    PostsService postsService;

    @PostMapping(value = "/")
    public Response createBusiness(@RequestBody Business business) {
        Business newBusiness = businessService.save(business);
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

    @GetMapping(value = "/approvedposts/{id}")
    public Response getApprovedPostsByBusinessId(@PathVariable("id") String businessId) {
        Optional<ApprovedPosts> approvedPosts = approvedPostsService.findByBusinessId(businessId);
        if(approvedPosts.isPresent())
            return new Response(true, "", approvedPosts.get());
        else
            return new Response(false, "Cannot find any business with the give ID", null);
    }

    @GetMapping(value = "/posts/{id}")
    public Response getPostsByBusinessId(@PathVariable("id") String businessId) {
        Optional<Posts> posts = postsService.findByBusinessId(businessId);
        if(posts.isPresent())
            return new Response(true, "", posts.get());
        else
            return new Response(false, "Cannot find any business with the give ID", null);
    }

    @PostMapping(value = "/newpost/{businessId}")
    public Response createNewBusinessPost(@PathVariable("businessId") String businessId, @RequestBody CreatePostDTO post) {
        Optional<Posts> posts = postsService.findByBusinessId(businessId);
        Posts newPosts;
        if(posts.isPresent()) {
            posts.get().addPosts(post.getPostId());
            newPosts = postsService.save(posts.get());
        } else
            return new Response(false, "Cannot find any business with the give ID", null);
        return new Response(true, "", newPosts);
    }

    @PostMapping(value = "/approvepost/{businessId}")
    public Response approveNewPost(@PathVariable("businessId") String businessId, @RequestBody CreatePostDTO post) {
        Optional<ApprovedPosts> approvedPosts = approvedPostsService.findByBusinessId(businessId);
        Optional<Posts> posts = postsService.findByBusinessId(businessId);

        ApprovedPosts newPosts;

        if(approvedPosts.isPresent()) {
            approvedPosts.get().addPost(post.getPostId());
            newPosts = approvedPostsService.save(approvedPosts.get());
        } else
            return new Response(false, "Cannot find any business with the give ID", null);

        if(posts.isPresent()) {
            posts.get().removePost(post.getPostId());
            postsService.save(posts.get());
        }
        return new Response(true, "", newPosts);
    }
}
