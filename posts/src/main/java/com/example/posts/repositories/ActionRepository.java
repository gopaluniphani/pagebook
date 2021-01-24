package com.example.posts.repositories;

import com.example.posts.entity.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends CrudRepository<Action, String> {

    @Query(value = "select count(*) from action where post_id=?1 and action_type=1 and " +
            "is_active=true", nativeQuery = true)
    int totalLikesByPostId(String postId);

    @Query(value = "select count(*) from action where post_id=?1 and action_type=2 and " +
            "is_active=true", nativeQuery = true)
    int totalDislikesByPostId(String postId);

    @Query(value = "select count(*) from action where post_id=?1 and action_type=3 and " +
            "is_active=true", nativeQuery = true)
    int totalWowEmojiByPostId(String postId);

    @Query(value = "select count(*) from action where post_id=?1 and action_type=4 and " +
            "is_active=true", nativeQuery = true)
    int totalSadEmojiByPostId(String postId);

    @Query(value = "select action_type from action where post_id=?1 and user_id=?2 " +
            "and is_active=true", nativeQuery = true)
    int performedActionByUserForPost(String postId, String userId);
}
