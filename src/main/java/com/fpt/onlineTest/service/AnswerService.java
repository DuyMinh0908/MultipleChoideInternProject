package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    //Get all answer
    List<Answer> getAllAnswer();

    //Get answer by id
    Optional<Answer> getAnswerById(Integer answerId);

    //Create answer
    Answer newAnswer(Answer answer);

    //Delete answer
    void deleteAnswer(Integer answerId);

    //Update answer
    Answer updateAnswer(Answer answer);
}
