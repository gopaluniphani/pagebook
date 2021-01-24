package com.example.posts.services.impl;

import com.example.posts.Constant;
import com.example.posts.entity.Comment;
import com.example.posts.repositories.CommentRepository;
import com.example.posts.repositories.PostRepository;
import com.example.posts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public int totalCommentsByPostId(String postId) {
        return commentRepository.totalCommentsByPostId(postId);
    }

    @Override
    public List<Comment> getUnApprovedComment(String postId) {
        return commentRepository.getUnapprovedComment(postId);
    }


    //todo: here we can add logic for business profile that if moderator is commenting on post then it should
    //be approved already.
    @Override
    public Comment addComment(Comment comment) {
        String profileType = postRepository.getProfileTypeByPostId(comment.getPostId());
        if(profileType.equals( Constant.PROFILE_TYPE_NORMAL))
        {
            comment.setApproved(true);
        }
        return commentRepository.save(comment);
    }



}
