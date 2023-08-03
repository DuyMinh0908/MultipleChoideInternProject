package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Query("select e from Exam e where e.courses.courseId=:courseId")
    List<Exam> findExamByCourseId(@Param("courseId") Integer courseId);

    @Transactional
    @Modifying
    @Query("delete from Exam e where e.courses.courseId=:courseId")
    void deleteExamByCourseId(@Param("courseId") Integer courseId);
}
