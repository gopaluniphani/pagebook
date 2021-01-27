package com.example.posts.repositories;

import com.example.posts.entity.PostsFeed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsFeedRepository extends PagingAndSortingRepository<PostsFeed, String> {

    @Query("select pf.postId from PostsFeed pf where pf.userId=:i")
    List<String> findPostsFeedByPage(@Param("i") String userId, Pageable pageable);
}
