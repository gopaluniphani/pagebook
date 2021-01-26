package com.example.posts.services.impl;

import com.example.posts.Constant;
import com.example.posts.entity.Comment;
import com.example.posts.model.CommentsDTO;
import com.example.posts.repositories.CommentRepository;
import com.example.posts.repositories.PBUserRepository;
import com.example.posts.repositories.PostRepository;
import com.example.posts.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PBUserRepository pbUserRepository;

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
        if(profileType.equals( Constant.PROFILE_TYPE_PUBLIC) || profileType.equals( Constant.PROFILE_TYPE_PRIVATE))
        {
            comment.setApproved(true);
        }
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentsDTO> getComments(String parentCommentId, String postId) {
        List<Comment> commentList = commentRepository.getComments(parentCommentId, postId);
        List<CommentsDTO> commentsDTOList = new ArrayList<>();
        for(Comment comment : commentList)
        {
            CommentsDTO commentsDTO = CommentsDTO.builder()
                    .comment( comment)
                    .userName( pbUserRepository.findUserNameByUserId(comment.getUserId()))
                    .build();
            commentsDTOList.add( commentsDTO);
        }
        return commentsDTOList;
    }

    @Override
    public Comment approveComment(String commentId) {
        return commentRepository.approveComment(commentId);
    }

    @Override
    public void unApproveComment(String commentId) {
        commentRepository.unApproveComment(commentId);
    }
}
