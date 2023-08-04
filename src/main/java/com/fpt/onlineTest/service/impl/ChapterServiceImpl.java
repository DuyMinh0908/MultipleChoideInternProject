package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.reponsitory.ChapterRepository;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Chapter createChapter( Chapter newChapter) {
        return chapterRepository.save(newChapter);
    }
    public boolean isCourseExist(Integer courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        return course.isPresent();
    }

    @Override
    public List<Chapter> getCourseChapters(Integer courseId) {
        return chapterRepository.findCourseSChapter(courseId);
    }

    @Override
    public Chapter updateChapter(Integer chapterId, Chapter chapter){
        Chapter existingChapter = chapterRepository.findById(chapterId).orElseThrow(()->new RuntimeException("Not found chapter with id: " + chapterId));
        if(chapter.getDescription()!= null){
            existingChapter.setDescription(chapter.getDescription());
        }
        return chapterRepository.save(existingChapter);
    }
    @Override
    public void deleteChapterById(Integer chapterId) {
        chapterRepository.deleteById(chapterId);
    }

    @Override
    public void deleteCourseSChapter(Integer courseId) {
        chapterRepository.deleteCourseSChapter(courseId);
    }

}
