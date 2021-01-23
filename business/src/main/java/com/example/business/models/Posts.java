package com.example.business.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Posts {
    @Id
    private String id;
    private String businessId;
    private List<String> posts;

    public Posts() {
        this.posts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }

    public void addPosts(String postId) {
        this.posts.add(postId);
    }

    public void removePost(String postId) {
        this.posts.remove(postId);
    }
}
