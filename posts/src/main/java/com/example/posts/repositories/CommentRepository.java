package com.example.posts.repositories;

import com.example.posts.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

    @Query(value = "select count(*) from comment where post_id=?1", nativeQuery = true)
    int totalCommentsByPostId(String postId);

    @Query("select comment from Comment comment where comment.isApproved=true and comment.postId=:i")
    List<Comment> getUnapprovedComment(@Param("i") String postId);

    //todo : sr. phani : develop a method to get comments by parent comment id

}
