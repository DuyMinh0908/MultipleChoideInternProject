package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Exam;

import java.util.List;
import java.util.Optional;

public interface ExamService {
//create
    Exam createExam(Exam exam);
//    update
    Exam updateExam(Integer examId, Exam exam);
//    find exam by id
    Optional<Exam> getExamById(Integer examId);
//    find exams by course id
    List<Exam> getExamsByCourseId(Integer courseId);
//    delete
    void deleteExamBydId(Integer examId);
//    delete course's exam
    void deleteExamByCourseId(Integer courseId);
}
