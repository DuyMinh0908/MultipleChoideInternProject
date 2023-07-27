package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Course;

import java.util.List;

public interface CourseService {
//    create course
    Course newCourse(Course course);

//    delete course by id
    void deleteCourseById(Integer courseId);

//    update course
    Course updateCourse(Integer courseId, Course course);

//    get all course
    List<Course> getAll();

//    get teacher course
    List<Course> getCoursesByTeacherId(Integer teacherId);

//    get student courses
    List<Course> getCoursesByStudentId(Integer studentId);

//    get popular course
    List<Course> getPopuLarCourses();


}
