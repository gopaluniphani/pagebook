package com.example.posts.repositories;

import com.example.posts.entity.PostsFeed;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostsFeedRepository extends PagingAndSortingRepository<PostsFeed, String> {

}
