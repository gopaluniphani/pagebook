package com.example.profile.dto;

import java.time.LocalDateTime;

public class AnalyticsDTO {

    private int channel_id;

    private String userId;

    private String category;

    private String type;

    private String typeId;

    private String action;

    //time:(java.sql.TimeStamp or any other timestamp format that can be converted to java.time.LocalDateTime)
    private LocalDateTime time;

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AnalyticsDTO{" +
                "channel_id=" + channel_id +
                ", userId='" + userId + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", typeId='" + typeId + '\'' +
                ", action='" + action + '\'' +
                ", time=" + time +
                '}';
    }
}
