package com.example.posts.repositories;

import com.example.posts.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {

    @Query(value = "select count(*) from comment where post_id=?1", nativeQuery = true)
    int totalCommentsByPostId(String postId);

    //todo : sr. phani : develop a method to get comments by parent comment id
}
