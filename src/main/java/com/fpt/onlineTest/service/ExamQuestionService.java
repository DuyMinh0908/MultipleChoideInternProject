package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import org.springframework.web.bind.annotation.PathVariable;

public interface ExamQuestionService {

    void createExamTest(Integer nums, Exam exam, String subject, String level);
}
