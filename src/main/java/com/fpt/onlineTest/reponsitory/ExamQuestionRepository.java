package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.ExamQuestion;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> {
    @Query("select eq from ExamQuestion eq where eq.exam.examId=:id")
    List<ExamQuestion> findExamQuestionByExamId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("delete from ExamQuestion eq where eq.exam.examId=:examId")
    void deleteExamAllQuestion(@Param("examId") Integer examId);

    @Query("select eq from ExamQuestion eq where eq.exam.examId=:examId and eq.question.questionId=:questionId")
    ExamQuestion findQuestionExist(@Param("examId") Integer examId,@Param("questionId")  Integer questionId);
}
