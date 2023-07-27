package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {
}
