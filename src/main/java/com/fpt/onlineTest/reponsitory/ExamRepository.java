package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ResultExam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
//get by course id
    @Query("select e from Exam e where e.courses.courseId=:courseId")
    List<Exam> findExamByCourseId(@Param("courseId") Integer courseId);
// delete by course id
    @Transactional
    @Modifying
    @Query("delete from Exam e where e.courses.courseId=:courseId")
    void deleteExamByCourseId(@Param("courseId") Integer courseId);
//    get exam question amount
    @Query("select e.numQuestion from Exam e where e.examId=:examId")
    Integer getExamQuestionAmount(@Param("examId") Integer examId);

    //    find finished exam of user
//    @Query("select re from ResultExam re " +
//            "left join User u on re.user.userId=u.userId " +
//            "left join Exam e on re.exam.examId=e.examId " +
//            "where re.user.userId=:userId")
    @Query("select e from Exam e " +
            "left join ResultExam re on e.examId = re.exam.examId " +
            "left join User u on re.user.userId=u.userId " +
            "where re.user.userId=:userId")
    Page<Exam> findFinishedExamOfUser(@Param("userId") Integer userId,Pageable pageable);

    //    find banded exam of user
    @Query("select re from ResultExam re " +
            "left join User u on re.user.userId=u.userId " +
            "left join Exam e on re.exam.examId=e.examId " +
            "where re.user.userId=:userId")
    Page<ResultExam> findBandedExamOfUser(@Param("userId") Integer userId, Pageable pageable);

    // find incoming exam of user
    @Query("select e from Exam e " +
            "join Course c on e.courses.courseId = c.courseId " +
            "join CoursesRegistration cr on c.courseId = cr.course.courseId " +
            "join User u on cr.user.userId = u.userId " +
            "where u.userId=:userId")
    Page<Exam> findIncomingExamOfUser(@Param("userId") Integer userId, Pageable pageable);

}
