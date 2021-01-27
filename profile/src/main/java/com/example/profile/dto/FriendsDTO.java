package com.example.profile.dto;

import org.apache.kafka.common.protocol.types.Field;

public class FriendsDTO {
    private String firstName;
    private String imgUrl;
    private String friendId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "FriendsDTO{" +
                "firstName='" + firstName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", friendId='" + friendId + '\'' +
                '}';
    }
}
