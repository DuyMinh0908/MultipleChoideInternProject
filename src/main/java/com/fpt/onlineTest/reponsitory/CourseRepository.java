package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

//    get courses by teacher id
    @Query("select new Course (c.courseId, c.courseName, c.imageCourse, c.numberStudent, c.status, c.subject) " +
            "from Course c " +
            "join Teacher t on c.teacher.id=t.id " +
            "where t.id = :teacherId")
    List<Course> findCoursesByTeacherId(@Param("teacherId") Integer teacherId);

//get courses by user id
    @Query("select new Course (c.courseId, c.courseName, c.imageCourse, c.numberStudent, c.status, c.subject) " +
            "from Course c " +
            "join CoursesRegistration cr on c.courseId = cr.course.courseId " +
            "where cr.user.userId = :userId")
    List<Course> findCoursesByStudentId(@Param("userId") Integer userId);

//    get popular courses
    @Query("select c from Course c order by c.numberStudent desc ")
    List<Course> findTopPopularCourse();

//    delete by id
}
