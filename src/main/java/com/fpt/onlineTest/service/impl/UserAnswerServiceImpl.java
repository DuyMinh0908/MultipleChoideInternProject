package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.ResultQuestion;
import com.fpt.onlineTest.model.UserAnswers;
import com.fpt.onlineTest.reponsitory.ResultQuestionRepository;
import com.fpt.onlineTest.reponsitory.UserAnswerRepository;
import com.fpt.onlineTest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Autowired
    ResultQuestionRepository resultQuestionRepository;

    @Override
    public List<UserAnswers> getAllUserAnswers() {
        return userAnswerRepository.findAll();
    }

    @Override
    public List<UserAnswers> getUserAnswerByUserId(Integer userId) {
        return userAnswerRepository.findUserAnswerByUserId(userId);
    }

    @Override
    public void createUserAnswer(List<UserAnswers> userAnswers) {
        for (UserAnswers userAnswer : userAnswers) {
            List<ResultQuestion> resultQuestions = resultQuestionRepository.findAll();
            boolean isCorrect = false;
            for (ResultQuestion resultQuestion : resultQuestions) {
                if (resultQuestion.getAnswer().getAnswerId().equals(userAnswer.getAnswer().getAnswerId())) {
                    isCorrect = true;
                    break;
                }
            }

            userAnswer.setStatus (isCorrect ? "true" : "false");
            userAnswerRepository.save(userAnswer);
        }
    }

    @Override
    public void deleteUserAnswer(Integer userAnswerId) {
        userAnswerRepository.deleteById(userAnswerId);
    }
}
