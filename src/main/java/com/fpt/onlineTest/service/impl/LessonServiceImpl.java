package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Lesson;
import com.fpt.onlineTest.reponsitory.LessonRepository;
import com.fpt.onlineTest.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getLessonByChapterId(Integer courseId) {
        return lessonRepository.findLessonsByChapterId(courseId);
    }

    public Lesson updateLesson(Integer lessonId, Lesson lesson) {
        Lesson existingLesson = lessonRepository.findById(lessonId).orElseThrow(()->new RuntimeException("Lesson not found!. id: "+lessonId));
        if(lesson.getLessonTitle()!=null){
            existingLesson.setLessonTitle(lesson.getLessonTitle());
        }
        return lessonRepository.save(existingLesson);
    }

    @Override
    public void deleteLessonById(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
