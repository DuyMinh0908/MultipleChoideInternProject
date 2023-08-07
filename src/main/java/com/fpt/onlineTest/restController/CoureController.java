package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete course by id
    @DeleteMapping("/courses/delete-course/{courseId}")
    public void deleteCourseById(@PathVariable Integer courseId) {
        try {
            courseService.deleteCourseById(courseId);
            new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update course
    @PutMapping("/courses/update-course/{courseId}")
    public ResponseEntity<Course> updateCourseByCourseId(@PathVariable Integer courseId, @RequestBody Course newCourse) {
        try {
            return new ResponseEntity<>(courseService.updateCourse(courseId, newCourse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    get all course
    @GetMapping("/courses")
    public ResponseEntity<Page<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(courseService.getAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    get course by teacher id
    @GetMapping("/courses/teacher-courses/{teacherId}")
    public ResponseEntity<Page<Course>> getCourseByTeacherId(
            @PathVariable Integer teacherId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(courseService.getCoursesByTeacherId(teacherId, pageable), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get course by id
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Integer courseId) {
        try {
            Optional<CourseDto> courseDto = courseService.getCourseDtoById(courseId);
            return courseDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get course by student id
    @GetMapping("/courses/student-courses/{userId}")
    public ResponseEntity<Page<Course>> getCoursesByStudentId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(courseService.getCoursesByStudentId(userId, pageable), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //    get top popular course
    @GetMapping("/courses/popular-courses")
    public ResponseEntity<List<Course>> getTopPopularCourses() {
        try {
            return new ResponseEntity<>(courseService.getPopuLarCourses(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get course image
    @GetMapping("/course/{courseId}/image")
    public ResponseEntity<Resource> getCourseImage(@PathVariable Integer courseId) {
        Optional<Course> courseOptional = courseService.getCourseById(courseId);
        if (courseOptional.isPresent()) {
            Course course = new Course();
            if (course.getImageCourse() != null) {
                Path imagePath = Paths.get(course.getImageCourse());
                Resource resource = new FileSystemResource(imagePath);

                if (resource.exists()) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG) // Hoặc MediaType.IMAGE_PNG tùy loại ảnh
                            .body(resource);
                }
            }

        }

        return ResponseEntity.notFound().build();
    }
}
