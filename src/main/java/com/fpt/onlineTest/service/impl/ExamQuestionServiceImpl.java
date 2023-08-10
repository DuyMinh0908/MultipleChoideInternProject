package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.reponsitory.ExamQuestionRepository;
import com.fpt.onlineTest.reponsitory.ExamRepository;
import com.fpt.onlineTest.reponsitory.QuestionRepository;
import com.fpt.onlineTest.service.ExamQuestionService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private ExamRepository examRepository;

    // create exam have {nums} questions, {subject} and {level}
    @Override
    public List<ExamQuestion> createExamTest(Integer nums, Exam exam, String subject, String level) {
        List<Integer> questionIdsList = questionRepository.getRandomQuestionId(subject, level);
        List<Integer> questionIds = questionIdsList.subList(0, Math.min(nums, questionIdsList.size()));

        List<ExamQuestion> examQuestionsList = new ArrayList<>();
        for (Integer questionId : questionIds) {
            Questions question = new Questions();
            question.setQuestionId(questionId);

            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setQuestion(question);
            examQuestion.setExam(exam);

            examQuestionsList.add(examQuestion);
        }
        return examQuestionRepository.saveAll(examQuestionsList);
    }

    //    create random exam have random level with {subject} and {nums} questions
    @Override
    public List<ExamQuestion> createExamTestWithRandomQuestion(Integer nums, Integer examId, String subject) {
        int totalNums = nums;

        int hardNums = (int) (totalNums * 0.2);
        int mediumNums = (int) (totalNums * 0.3);
        int easyNums = totalNums - hardNums - mediumNums;

        List<Integer> hardQidList = questionRepository.getRandomQuestionId(subject, "hard");
        List<Integer> hardList = hardQidList.subList(0, Math.min(hardNums, hardQidList.size()));

        List<Integer> mediumQidList = questionRepository.getRandomQuestionId(subject, "medium");
        List<Integer> mediumList = mediumQidList.subList(0, Math.min(mediumNums, mediumQidList.size()));

        List<Integer> easyQidList = questionRepository.getRandomQuestionId(subject, "easy");
        List<Integer> easyList = easyQidList.subList(0, Math.min(easyNums, easyQidList.size()));

        List<ExamQuestion> examQuestionList = new ArrayList<>();
        for (Integer hardQid : hardList) {
            Exam exam = new Exam();
            exam.setExamId(examId);
            Questions question = new Questions();
            question.setQuestionId(hardQid);

            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExam(exam);
            examQuestion.setQuestion(question);

            examQuestionList.add(examQuestion);
        }

        for (Integer mediumQid : mediumList) {
            Exam exam = new Exam();
            exam.setExamId(examId);
            Questions question = new Questions();
            question.setQuestionId(mediumQid);

            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setQuestion(question);
            examQuestion.setExam(exam);

            examQuestionList.add(examQuestion);
        }

        for (Integer easyQid : easyList) {
            Exam exam = new Exam();
            exam.setExamId(examId);
            Questions question = new Questions();
            question.setQuestionId(easyQid);

            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setQuestion(question);
            examQuestion.setExam(exam);

            examQuestionList.add(examQuestion);
        }
        return examQuestionRepository.saveAll(examQuestionList);
    }

    @Override
    public ExamQuestion addSingleQuestion(ExamQuestion examQuestion) {
        return examQuestionRepository.save(examQuestion);
    }

    @Override
    public List<ExamQuestion> addSelectedQuestion(List<ExamQuestion> examQuestions) {
        return examQuestionRepository.saveAll(examQuestions);
    }

    @Override
    public Page<ExamQuestion> getExamTestQuestions(Integer examId, Pageable pageable) {
        return examQuestionRepository.findExamQuestionByExamId(examId, pageable);
    }

    //delete 1 question
    @Override
    public void deleteQuestionById(Integer questionId) {
        examQuestionRepository.deleteById(questionId);
    }

    // delete all exam question
    @Override
    public void deleteExamAllQuestion(Integer examId) {
        examQuestionRepository.deleteExamAllQuestion(examId);
    }

    // delete selected question
    @Override
    public void deleteListOfQuestion(List<Integer> listId) {
        examQuestionRepository.deleteAllByIdInBatch(listId);
    }
}
