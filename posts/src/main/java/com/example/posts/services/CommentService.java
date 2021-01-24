package com.example.posts.services;

import com.example.posts.entity.Comment;

import java.util.List;

public interface CommentService {

    int totalCommentsByPostId(String postId);

    List<Comment> getUnApprovedComment(String postId);

    Comment addComment(Comment comment);
}
