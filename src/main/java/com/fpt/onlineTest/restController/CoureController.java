package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CoureController {
    @Autowired
    CourseService courseService;

    //    create course
    @PostMapping("/courses/create-course")
    public ResponseEntity<Course> createNewCourse(@RequestBody Course newCourse) {
        try {
            return new ResponseEntity<>(courseService.newCourse(newCourse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete course by id
    @DeleteMapping("/courses/delete-course/courseId:/{courseId}")
    public void deleteCourseById(@PathVariable Integer courseId) {
        try {
            courseService.deleteCourseById(courseId);
            new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update course
    @PutMapping("/courses/update-course/courseId:/{courseId}")
    public ResponseEntity<Course> updateCourseByCourseId(@PathVariable Integer courseId,@RequestBody Course newCourse) {
        try {
            return new ResponseEntity<>(courseService.updateCourse(courseId, newCourse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    get all course
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        try {
            return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    get course by teacher id
    @GetMapping("/courses/teacher-courses/teacherId:/{teacherId}")
    public ResponseEntity<List<Course>> getCourseByTeacherId(@PathVariable Integer teacherId) {
        try {
            return new ResponseEntity<>(courseService.getCoursesByTeacherId(teacherId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get course by student id
    @GetMapping("/courses/student-courses/studentId:/{userId}")
    public List<Course> getCoursesByStudentId(@PathVariable Integer userId) {
        return courseService.getCoursesByStudentId(userId);

    }

//    get top popular course
    @GetMapping("/courses/popular-courses")
    public ResponseEntity<List<Course>> getTopPopularCourses(){
        try {
            return new ResponseEntity<>(courseService.getPopuLarCourses(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
