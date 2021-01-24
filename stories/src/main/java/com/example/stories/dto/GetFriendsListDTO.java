package com.example.stories.dto;

import java.util.List;

public class GetFriendsListDTO {
    private List<String> friendsList;
    private String storyId;

    public List<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
