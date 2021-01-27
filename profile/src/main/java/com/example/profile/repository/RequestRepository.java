package com.example.profile.repository;

import com.example.profile.entity.Profile;
import com.example.profile.entity.Request;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request,String> {

    @Transactional(readOnly = true)
    @Query(value = "select first_name from profile where user_id=?1", nativeQuery = true)
    String findFriendName(String friendId);

    @Transactional(readOnly = true)
    @Query(value = "select requestor_id from Request where user_id=?1", nativeQuery = true)
    List<String> findRequestorId(String userId);

    @Transactional
    @Modifying
    @Query("delete from Request pr where pr.requestorId=:i")
    void deleteRequestById(@Param("i") String requestorId);

}
