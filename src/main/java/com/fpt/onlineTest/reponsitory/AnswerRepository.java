package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
