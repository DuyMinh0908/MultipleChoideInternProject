package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.TeacherRepository;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Course newCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course updateCourse(Integer courseId, Course course) {
        Optional<Course> isExistingCourse = courseRepository.findById(courseId);
        if (isExistingCourse.isPresent()) {
            Course existingCourse = isExistingCourse.get();
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setNumberStudent(course.getNumberStudent());
            existingCourse.setImageCourse(course.getImageCourse());
            existingCourse.setStatus(course.getStatus());
            existingCourse.setSubject(course.getSubject());
            return courseRepository.save(existingCourse);
        } else {
            // Handle the case when the course with the provided courseId is not found
            // For example, you can throw an exception or return null.
            throw new EntityNotFoundException("Course with courseId " + courseId + " not found");
        }
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
        return courseRepository.findCoursesByStudentId(studentId);
    }

    @Override
    public List<Course> getPopuLarCourses() {
        List<Course> topPopularCourses = courseRepository.findTopPopularCourse();
        return topPopularCourses.subList(0, Math.min(5, topPopularCourses.size()));
    }

    //  get teacher id
    Integer getTeacherId() {
        return null;
    }
}