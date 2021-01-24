package com.example.posts.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


 /*Actions
 NoAction == 0
 Like == 1
 disLike == 2
 WowEmoji == 3
 SadEmoji == 4*/
//To make this Action generic for postAction and commentAction, we have to add commentId and one flag
// to differentiate Comment and Post. (Keep it for later part.)


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Action {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String actionId;

    private String postId;

    private String userId;

    //same as performedAction
    private int actionType;

    @CreatedDate
    private String actionTime;

    private boolean isActive;

    @Override
    public String toString() {
        return "Action{" +
                "actionId='" + actionId + '\'' +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", actionType='" + actionType + '\'' +
                ", actionTime='" + actionTime + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
