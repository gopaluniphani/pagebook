package com.example.stories.dto;

import com.example.stories.models.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryFeedDTO {
    private List<Story> stories;

    public StoryFeedDTO() {
        this.stories = new ArrayList<>();
    }

    public void addToStories(Story story) {
        this.stories.add(story);
    }
}
