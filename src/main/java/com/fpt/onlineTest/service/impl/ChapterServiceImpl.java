package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.reponsitory.ChapterRepository;
import com.fpt.onlineTest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public Chapter createChapter(Chapter newChapter) {
        return chapterRepository.save(newChapter);
    }

    @Override
    public Optional<Chapter> getCourseChapter(Integer chapterId) {
        return chapterRepository.findById(chapterId);
    }

    @Override
    public List<Chapter> getAllCourseChapters() {
        return chapterRepository.findAll();
    }

    @Override
    public Chapter updateChapter(Integer chapterId, Chapter chapter){
        Optional<Chapter> isExistingChapter = chapterRepository.findById(chapterId);
        if(isExistingChapter.isPresent()){
            Chapter existingChapter = isExistingChapter.get();
            existingChapter.setDescription(chapter.getDescription());
        }
        return null;
    }
    @Override
    public void deleteChapterById(Integer chapterId) {
        chapterRepository.deleteById(chapterId);
    }
    @Override
    public void deleteAllCourseSChapter(Integer courseId) {
        chapterRepository.deleteCourseSChapter(courseId);
    }
}
