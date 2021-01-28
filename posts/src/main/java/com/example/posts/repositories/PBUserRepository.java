package com.example.posts.repositories;

import com.example.posts.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PBUserRepository extends CrudRepository<User, String> {

    @Query(value = "select user_name from pbuser where user_id=?1",nativeQuery = true)
    String findUserNameByUserId(String userId);

    @Query(value = "select  user_imgurl from pbuser where user_id=?1",nativeQuery = true)
    String findUserImgByUserId(String userId);
}
