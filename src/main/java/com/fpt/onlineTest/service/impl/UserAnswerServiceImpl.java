package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.UserAnswers;
import com.fpt.onlineTest.reponsitory.UserAnswerRepository;
import com.fpt.onlineTest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Override
    public List<UserAnswers> getAllUserAnswers() {
        return userAnswerRepository.findAll();
    }

    @Override
    public List<UserAnswers> getUserAnswerByUserId(Integer userId) {
        return userAnswerRepository.findUserAnswerByUserId(userId);
    }

    @Override
    public UserAnswers createUserAnswer(UserAnswers userAnswer) {
        return userAnswerRepository.save(userAnswer);
    }
}
