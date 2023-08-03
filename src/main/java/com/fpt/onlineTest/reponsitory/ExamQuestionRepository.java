package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> { }
