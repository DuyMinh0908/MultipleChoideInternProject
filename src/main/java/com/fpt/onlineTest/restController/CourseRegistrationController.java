package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.CoursesRegistrationDto;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.CoursesRegistration;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.CourseRegistrationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CourseRegistrationController {
    @Autowired
    private CourseRegistrationService crs;

    @PostMapping("/course/subscribe")
    public ResponseEntity<CoursesRegistration> courseRegistration(@RequestBody CoursesRegistration coursesRegistration) {
        try {
            return new ResponseEntity<>(crs.courseRegistration(coursesRegistration), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> courseRegistration(Integer crId) {
        try {
            crs.cancelRegistration(crId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{courseId}/users")
    public ResponseEntity<CoursesRegistrationDto> courseUsers(@PathVariable Integer courseId, Pageable pageable) {
        try {
            return new ResponseEntity<>(crs.getCourseUsers(courseId,pageable),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<HttpStatus> afda(@PathVariable Integer courseId) {
        crs.getCourseUsers(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    }
