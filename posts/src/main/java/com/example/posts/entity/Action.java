package com.example.posts.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


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
@Getter
@Setter
@ToString
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
}
