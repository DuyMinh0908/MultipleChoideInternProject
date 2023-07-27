package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterService {
//  -------  create  -------
   Chapter createChapter(Chapter newChapter);
//  -------  read  -------
//    get 1 course's chapter
    Optional<Chapter> getCourseChapter(Integer chapterId);
//    get all course's chapters
    List<Chapter> getAllCourseChapters();
//  -------  update  -------
    Chapter updateChapter(Integer chapterId, Chapter chapter);
//  -------  delete  -------
    void deleteChapterById(Integer chapterId);
    void deleteAllCourseSChapter(Integer courseId);
}
