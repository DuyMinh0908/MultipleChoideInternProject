package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.reponsitory.ExamQuestionRepository;
import com.fpt.onlineTest.reponsitory.ExamRepository;
import com.fpt.onlineTest.reponsitory.QuestionRepository;
import com.fpt.onlineTest.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Override
    public void createExamTest(Integer nums, Exam exam, String subject, String level) {
        List<Integer> questionIdsList = questionRepository.getRandomQuestionId(subject,level);
        List<Integer>questionIds = questionIdsList.subList(0,Math.min(nums, questionIdsList.size()));
        System.out.println(questionIds);
        List<ExamQuestion> examQuestions = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Questions questions = new Questions();
            questions.setQuestionId(questionId);

            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setQuestion(questions);
            examQuestion.setExam(exam);

            examQuestions.add(examQuestion);
        }
        examQuestionRepository.saveAll(examQuestions);
    }
}
