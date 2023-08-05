package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    //    create course
    Course newCourse(Course course);

    //    delete course by id
    void deleteCourseById(Integer courseId);

    //    update course
    Course updateCourse(Integer courseId, Course course);

    //    get all course
    Page<Course> getAll(Pageable pageable);

    //    get teacher course
    Page<Course> getCoursesByTeacherId(Integer teacherId, Pageable pageable);

    //    get student courses
    Page<Course> getCoursesByStudentId(Integer studentId, Pageable pageable);

    //    get popular course
    List<Course> getPopuLarCourses();

    // get course by id
    Optional<CourseDto> getCourseDtoById(Integer courseId);
    Optional<Course> getCourseById(Integer courseId);
}
