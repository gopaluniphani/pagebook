package com.example.posts.repositories;

import com.example.posts.entity.Comment;
import com.example.posts.entity.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

    @Transactional(readOnly = true)
    @Query(value = "select count(*) from comment where post_id=?1", nativeQuery = true)
    int totalCommentsByPostId(String postId);

    @Transactional(readOnly = true)
    @Query("select comment from Comment comment where comment.isApproved=true and comment.postId=:i")
    List<Comment> getUnapprovedComment(@Param("i") String postId);

    //todo : sr. phani : develop a method to get comments by parent comment id
    @Query("select comment from Comment comment where comment.parentCommentId=:p and " +
            "comment.postId=:i")
    List<Comment> getComments(@Param("p") String parentCommentId, @Param("i") String postId);

    @Transactional
    @Modifying
    @Query("update Comment comment set comment.isApproved=true where comment.commentId=:i")
    Comment approveComment(@Param("i") String commentId);
}
