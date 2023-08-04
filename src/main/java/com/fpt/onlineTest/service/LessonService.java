package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Lesson;

import java.util.List;

public interface LessonService {
//    create
    Lesson createLesson(Lesson lesson);
//    get by course id
    List<Lesson> getLessonByChapterId(Integer courseId);
//    update
    Lesson updateLesson(Integer lessonId, Lesson lesson);

//    delete by id
    void deleteLessonById(Integer lessonId);
}
