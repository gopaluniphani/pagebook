package com.example.profile.repository;

import com.example.profile.entity.Friend;
import com.example.profile.entity.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRepository extends CrudRepository<Friend,String> {


    @Transactional(readOnly = true)
    @Query(value = "select first_name from profile where user_id=?1", nativeQuery = true)
    String findFriendName(String friendId);

    @Transactional(readOnly = true)
    @Query(value = "select friend_id from Friend where user_id=?1", nativeQuery = true)
    List<String> findFriendId(String userId);
}
