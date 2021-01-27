package com.example.posts.model;


import com.example.posts.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PostDTO {

    private Post post;

    private String userImgURL;

    private String userName;

    private int totalComments;

    private int totalLikes;

    private int totalDislikes;

    private int totalWowEmoji;

    private int totalSadEmoji;

    //It is for mapping action which performed by User.
    private int performedAction;

    //todo : sr. phani : need to have a list of first level or all level comments, should contain list of action performed for each comments
    //todo : sr. phani : list of actions performed at post level
}
