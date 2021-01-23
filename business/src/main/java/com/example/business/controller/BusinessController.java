package com.example.business.controller;

import com.example.business.models.Business;
import com.example.business.models.ApprovedPosts;
import com.example.business.models.Response;
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
    public Response getPostsByBusinessId(@PathVariable("id") String businessId) {
        ApprovedPosts approvedPosts = approvedPostsService.findByBusinessId(businessId);
        return new Response(true, "", approvedPosts);
    }
}
