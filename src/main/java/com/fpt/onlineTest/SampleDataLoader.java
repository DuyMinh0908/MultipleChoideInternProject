//package com.fpt.onlineTest;
//
//import com.fpt.onlineTest.model.*;
//import com.fpt.onlineTest.reponsitory.*;
//import com.github.javafaker.Faker;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//public class SampleDataLoader implements CommandLineRunner {
//    private final CourseRepository courseRepository;
//    private final TeacherRepository teacherRepository;
//    private final UserReponsitory userReponsitory;
//    private final CoursesRegistrationRepository coursesRegistrationRepository;
//    private final ContentCourseRepository contentCourseRepository;
//    private final Faker faker;
//
//    public SampleDataLoader(CourseRepository courseRepository, TeacherRepository teacherRepository, UserReponsitory userReponsitory, CoursesRegistrationRepository coursesRegistrationRepository, ContentCourseRepository contentCourseRepository, Faker faker) {
//        this.courseRepository = courseRepository;
//        this.teacherRepository = teacherRepository;
//        this.userReponsitory = userReponsitory;
//        this.coursesRegistrationRepository = coursesRegistrationRepository;
//        this.contentCourseRepository = contentCourseRepository;
//        this.faker = faker;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // create 10 rows of fake course data
//
//
//    }
//}
