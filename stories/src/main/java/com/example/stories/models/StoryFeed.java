package com.example.stories.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//todo : Sr. Phani : This should be a DTO not a document
@Document
public class StoryFeed {

    @Id
    private String id;
    private String userId;
    private List<String> stories;

    public StoryFeed() {
        this.stories = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getStories() {
        return stories;
    }

    public void setStories(List<String> stories) {
        this.stories = stories;
    }

    public void addStory(String storyId) {
        this.stories.add(storyId);
    }

    public void removeStory(String storyId) {
        this.stories.remove(storyId);
    }
}
