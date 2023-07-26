package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

//    @Query(value = "select c.courseName, c.imageCourse, c.numberStudent, c.status, c.subject,cc.description from Course c left join CoursesRegistration cr on c.courseId = cr.course.courseId left join ContentCourse cc on c.courseId= cc.courses.courseId where cr.teacher.id = :teacherId",nativeQuery = true)
//    @Query(value = "select courses.course_name, courses.image_course, courses.number_student, courses.status, courses.subject,ccourses.description " +
//            "from courses " +
//            "left join courses_registration " +
//            "on courses.course_id = courses_registration.course_id " +
//            "left join content_courses " +
//            "on courses.course_id= content_courses.course_id " +
//            "where courses_registration.teacher_id = :teacherId",nativeQuery = true)
    @Query(value = "select courses.course_name, courses.image_course, courses.number_student, courses.status, courses.subject,content_courses.description from courses " +
            "left join courses_registration on courses.course_id = courses_registration.course_id " +
            "left join  content_courses on courses.course_id = content_courses.course_id" +
            " where courses_registration.teacher_id=2", nativeQuery = true)
    List<Course> findCoursesByTeacherId(@Param("teacherId") Integer teacherId);

}
