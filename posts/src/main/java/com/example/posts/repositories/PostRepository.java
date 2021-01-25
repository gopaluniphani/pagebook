package com.example.posts.repositories;

import com.example.posts.entity.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, String> {

    @Transactional(readOnly = true)
    @Query("select ps from Post ps where ps.userId=:i")
    List<Post> findPostByUserId(@Param("i") String userId);

    @Transactional(readOnly = true)
    @Query("select ps.userId from Post ps where ps.postId=:i")
    String findUserIdByPostId(@Param("i") String postId);

    @Transactional(readOnly = true)
    @Query("select ps from Post ps where ps.isApproved=false and ps.businessId=:i")
    List<Post> getUnApprovedPost(@Param("i") String businessId);

    @Transactional(readOnly = true)
    @Query("select ps from Post ps where ps.isApproved=true and ps.businessId=:i")
    List<Post> getBusinessPost(@Param("i") String businessId);

    @Transactional(readOnly = true)
    @Query("select ps.profileType from Post ps where ps.postId=:p")
    String getProfileTypeByPostId(@Param("p") String postId);

    @Transactional
    @Modifying
    @Query("update Post ps set ps.isApproved=true where ps.postId=:i")
    Post approvePost(@Param("i") String postId);
}
