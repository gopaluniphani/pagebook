package com.example.posts.services;

import com.example.posts.entity.Comment;
import com.example.posts.model.CommentsDTO;

import java.util.List;

public interface CommentService {

    int totalCommentsByPostId(String postId);

    List<Comment> getUnApprovedComment(String postId);

    Comment addComment(Comment comment);

    List<CommentsDTO> getComments(String parentCommentId, String postId);

    Comment approveComment(String commentId);

    void unApproveComment(String commentId);
}
