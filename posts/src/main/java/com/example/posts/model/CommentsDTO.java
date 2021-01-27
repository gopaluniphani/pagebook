package com.example.posts.model;

import com.example.posts.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommentsDTO {
    //need to fill content in this
    //comment text and list of actions performed
    private String userName;

    private Comment comment;

}
