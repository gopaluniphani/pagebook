package com.example.posts.repositories;

import com.example.posts.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {

    @Transactional(readOnly = true)
    @Query("select ps from Post ps where ps.userId=:i")
    List<Post> findPostByUserId(@Param("i") String userId);

    @Query("select ps.userId from Post ps where ps.postId=:i")
    String findUserIdByPostId(@Param("i") String postId);
}
