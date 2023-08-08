package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.CoursesRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRegistrationRepository extends JpaRepository<CoursesRegistration,Integer> {
    @Query("select cr.user.userId from CoursesRegistration cr where cr.course.courseId =:courseId")
    List<Integer> findUserIdsByCourseId(@Param("courseId") Integer courseId);
}
