package com.example.posts.repositories;

import com.example.posts.entity.Action;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends CrudRepository<Action, String> {

    @Query(value = "select count(*) from action where post_id=?1 and action_type=1", nativeQuery = true)
    int totalLikesByPostId(String postId);

    @Query(value = "select count(*) from action where post_id=?1 and action_type=2", nativeQuery = true)
    int totalDislikesByPostId(String postId);

    @Query(value = "select count(*) from action where post_id=?1 and action_type=3", nativeQuery = true)
    int totalWowEmojiByPostId(String postId);

    @Query(value = "select count(*) from action where post_id=?1 and action_type=4", nativeQuery = true)
    int totalSadEmojiByPostId(String postId);

    @Query(value = "select action_type from action where post_id=?1 and user_id=?2", nativeQuery = true)
    Optional<Integer> performedActionByUserForPost(String postId, String userId);

    @Query("select act.actionId from Action act where act.userId=:u and act.postId=:p")
    String findActionIdByPostIdAndUserId(@Param("u") String userId, @Param("p") String postId);

    @Query("select act.userId from Action act where act.postId=:p and act.actionType=1")
    List<String> getUserWhoLiked(@Param("p") String postId);

    @Query("select act.userId from Action act where act.postId=:p and act.actionType=2")
    List<String> getUserWhoDisliked(@Param("p") String postId);

    @Query("select act.userId from Action act where act.postId=:p and act.actionType=3")
    List<String> getUserOfWowEmoji(@Param("p") String postId);

    @Query("select act.userId from Action act where act.postId=:p and act.actionType=4")
    List<String> getUserOfSadEmoji(@Param("p") String postId);
}
