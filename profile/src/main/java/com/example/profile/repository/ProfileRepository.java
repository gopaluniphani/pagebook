package com.example.profile.repository;

import com.example.profile.entity.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,String> {

    @Transactional
    @Modifying
    @Query("update Profile pr set pr.totalFriends=:q where pr.userId=:i")
    void updateTotalFriend(@Param("i") String userId, @Param("q") int totalFriends);

    @Transactional(readOnly = true)
    @Query(value = "select total_friends from profile where user_id=?1", nativeQuery = true)
    String findTotalFriendById(String userId);

    @Transactional(readOnly = true)
    @Query(value = "select img_url from profile where user_id=?1", nativeQuery = true)
    String getImgUrlById(String userId);

    @Transactional(readOnly = true)
    @Query(value = "select profile_type from profile where user_id=?1", nativeQuery = true)
    String getProfileTypeById(String userId);

    @Transactional(readOnly = true)
    @Query(value = "select interest from profile where user_id=?1", nativeQuery = true)
    String getInterestById(String userId);

    @Transactional(readOnly = true)
    @Query(value = "select first_name from profile where user_id=?1", nativeQuery = true)
    String findUserNameById(String userId);

    @Transactional(readOnly = true)
    @Query(value = "select * from profile where email=?1", nativeQuery = true)
    Profile getProfileByEmail(String email);

    @Transactional(readOnly = true)
    @Query(value = "select * from profile where user_id=?1", nativeQuery = true)
    Profile getProfileById(String userId);

    @Transactional
    @Modifying
    @Query("update Profile pr set pr.imgUrl=:r where pr.userId=:i")
    void updateImg(@Param("i") String userId, @Param("r") String imgUrl);
}
