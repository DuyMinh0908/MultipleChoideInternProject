package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Address;
import com.fpt.onlineTest.model.Person;
import com.fpt.onlineTest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReponsitory extends JpaRepository< User,Integer> {
    @Query("select u from User u where u.userId=:userId")
    Page<User> findCourseSUsers(@Param("userId") List<Integer> userId, Pageable pageable);
}
