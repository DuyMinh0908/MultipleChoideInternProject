package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.ExamRepository;
import com.fpt.onlineTest.reponsitory.UserRepository;
import com.fpt.onlineTest.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Exam createExam(Exam exam) {
        Course course = courseRepository.findById(exam.getCourses().getCourseId()).orElse(null);
        if (course == null)
            return null;
        else
            exam.setCourses(course);
        return examRepository.save(exam);
    }

    @Override
    public Exam updateExam(Integer examId, Exam exam) {
        Exam exitingExam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("exam not found! id: " + examId));
        if (exitingExam.getNumQuestion() != null)
            exitingExam.setNumQuestion(exam.getNumQuestion());

        if (exitingExam.getDuration() != null)
            exitingExam.setDuration(exam.getDuration());

        return examRepository.save(exitingExam);
    }

    @Override
    public Optional<Exam> getExamById(Integer examId) {
        return examRepository.findById(examId);
    }

    @Override
    public List<Exam> getExamsByCourseId(Integer courseId) {
        return examRepository.findExamByCourseId(courseId);
    }

    @Override
    public void deleteExamBydId(Integer examId) {
        examRepository.deleteById(examId);
    }

    @Override
    public void deleteExamByCourseId(Integer courseId) {
        examRepository.deleteExamByCourseId(courseId);
    }

    @Override
    public Page<Exam> getExamByUserId(Integer userId, Pageable pageable) {
//        Page<Exam> userExamList = new ArrayList<>();
//        Page<Exam> finishedExamOfUserList = examRepository.findFinishedExamOfUser(userId,pageable);
//        Page<Exam> bandedExamOfUserList = new ArrayList<>();
//        // add finished exam to user exam list
//        for (Exam finishedExamOfUser: finishedExamOfUserList) {
//                userExamList.add(finishedExamOfUser);
//        }
//
//        // add banded exam to user exam list
//        for (Exam bandedExamOfUser: bandedExamOfUserList) {
//                userExamList.add(bandedExamOfUser);
//        }

        // incoming exam
        return examRepository.findFinishedExamOfUser(userId,pageable);


//        return null;
    }
}
