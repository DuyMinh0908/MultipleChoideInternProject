package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CoureController {
    @Autowired
    CourseService courseService;

    @GetMapping("/courses/{teacherId}")
    public List<Course> getCourseByTeacherId(@PathVariable Integer teacherId){
        return courseService.getCoursesByTeacherId(teacherId);
    }
}
