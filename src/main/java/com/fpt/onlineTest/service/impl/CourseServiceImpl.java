package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.TeacherRepository;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Course newCourse(Course course) {
//        Teacher teacher = teacherRepository.findById();
        return null;
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course updateCourse(Integer courseId, Course course) {
        Course updatedCourse = new Course();
        updatedCourse.setContentCourses(course.getContentCourses());
        updatedCourse.setCourseName(course.getCourseName());
        updatedCourse.setNumberStudent(course.getNumberStudent());
        updatedCourse.setImageCourse(course.getImageCourse());
        updatedCourse.setStatus(course.getStatus());
        updatedCourse.setSubject(course.getSubject());
        return courseRepository.save(updatedCourse);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByTeacherId(Integer teacherId) {
        return courseRepository.findCoursesByTeacherId(teacherId);
    }

    @Override
    public List<Course> getCoursesByStudentId(Integer studentId) {
        return null;
    }

    @Override
    public List<Course> getPopuLarCourses() {
        return null;
    }
}
