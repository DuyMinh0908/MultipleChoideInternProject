package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    //    delete course's chapter
    @Query("delete from Chapter ch where ch.courses.courseId=:courseId")
    void deleteCourseSChapter(Integer courseId);
}
