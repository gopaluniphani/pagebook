package com.example.posts.repositories;

import com.example.posts.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PBUserRepository extends CrudRepository<User, String> {

    @Query("select us.userName from User us where us.userId=:i")
    String findUserNameByUserId(@Param("i") String userId);

    @Query("select us.userImgURL from User us where us.userId=:i")
    String findUserImgByUserId(@Param("i") String userId);
}
