package com.example.posts.services.impl;

import com.example.posts.repositories.CommentRepository;
import com.example.posts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public int totalCommentsByPostId(String postId) {
        return commentRepository.totalCommentsByPostId(postId);
    }
}
