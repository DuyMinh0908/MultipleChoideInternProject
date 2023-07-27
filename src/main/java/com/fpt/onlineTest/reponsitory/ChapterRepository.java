package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {

    // create current course chapter
    @Modifying
    @Query(value = "insert into chapters values(:title,:courseId)", nativeQuery = true)
    Chapter createCourseSChapter(@Param("title") String title, @Param("courseId") Integer courseId);

//    delete course's chapter
    @Query("delete from Chapter ch where ch.courses.courseId=:courseId")
    void deleteCourseSChapter(Integer courseId);


}
