//package com.fpt.onlineTest;
//
//import com.fpt.onlineTest.model.Course;
//import com.fpt.onlineTest.model.CoursesRegistration;
//import com.fpt.onlineTest.model.Teacher;
//import com.fpt.onlineTest.model.User;
//import com.fpt.onlineTest.reponsitory.CourseRepository;
//import com.fpt.onlineTest.reponsitory.CoursesRegistrationRepository;
//import com.fpt.onlineTest.reponsitory.TeacherRepository;
//import com.fpt.onlineTest.reponsitory.UserReponsitory;
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
//    private final Faker faker;
//
//    public SampleDataLoader(CourseRepository courseRepository, TeacherRepository teacherRepository, UserReponsitory userReponsitory, CoursesRegistrationRepository coursesRegistrationRepository, Faker faker) {
//        this.courseRepository = courseRepository;
//        this.teacherRepository = teacherRepository;
//        this.userReponsitory = userReponsitory;
//        this.coursesRegistrationRepository = coursesRegistrationRepository;
//        this.faker = faker;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        // create 10 rows of fake course data
//        List<Course> courses = IntStream.rangeClosed(1, 10)
//                .mapToObj(i -> new Course(
//                        faker.name().lastName(),
//                        faker.avatar().image(),
//                        (int) faker.number().randomNumber(),
//                        faker.bool().bool(),
//                        faker.address().city()
//                )).collect(Collectors.toList());
//        courseRepository.saveAll(courses);
//
//        // create 10 rows of fake teacher data
//        List<Teacher> teachers = IntStream.rangeClosed(1, 10)
//                .mapToObj(i -> new Teacher(
//                        faker.name().lastName(),
//                        faker.pokemon().name(),
//                        faker.name().fullName(),
//                        faker.lorem().characters(),
//                        faker.phoneNumber().phoneNumber(),
//                        faker.address().city(),
//                        faker.avatar().image()
//                )).collect(Collectors.toList());
//        teacherRepository.saveAll(teachers);
//
//        // create 10 rows of fake teacher data
//        List<User> users = IntStream.rangeClosed(1, 10)
//                .mapToObj(i -> new User(
//                        faker.name().lastName(),
//                        faker.pokemon().name(),
//                        faker.name().fullName(),
//                        faker.lorem().characters(),
//                        faker.phoneNumber().phoneNumber(),
//                        faker.address().city(),
//                        faker.avatar().image()
//                )).collect(Collectors.toList());
//        userReponsitory.saveAll(users);
//
//        List<CoursesRegistration> coursesRegistrations = IntStream.range(1, 10)
//                .mapToObj(i -> new CoursesRegistration(
//                        new User(),
//                        new Course(),
//                        new Teacher()
//                )).collect(Collectors.toList());
//        coursesRegistrationRepository.saveAll(coursesRegistrations);
//    }
//}
