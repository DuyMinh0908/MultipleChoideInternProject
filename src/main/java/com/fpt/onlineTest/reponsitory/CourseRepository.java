package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    //    get courses by teacher id
    @Query("select c from Course c where c.teacher.id = :teacherId")
    Page<Course> findCoursesByTeacherId(@Param("teacherId") Integer teacherId, Pageable pageable);

    //get courses by user id
    @Query("select new Course (c.courseId, c.courseName, c.imageCourse, c.numberStudent, c.status, c.subject) " +
            "from Course c " +
            "join CoursesRegistration cr on c.courseId = cr.course.courseId " +
            "where cr.user.userId = :userId")
    Page<Course> findCoursesByStudentId(@Param("userId") Integer userId, Pageable pageable);

    //    get popular courses
    @Query("select c from Course c order by c.numberStudent desc ")
    List<Course> findTopPopularCourse();

    @Query()
    Course findCourseWithChaptersAndLessonsByCourseId(@Param("courseId") Integer courseId);
}
