package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> { }
