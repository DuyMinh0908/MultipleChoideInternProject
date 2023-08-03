package com.fpt.onlineTest;

import com.fpt.onlineTest.model.*;
import com.fpt.onlineTest.reponsitory.*;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {
//    private final CourseRepository courseRepository;
//    private final TeacherRepository teacherRepository;
    private final UserReponsitory userReponsitory;
//    private final CoursesRegistrationRepository coursesRegistrationRepository;
//    private final ContentCourseRepository contentCourseRepository;
    private  final  ChapterRepository chapterRepository;
    private final  LessonRepository lessonRepository;
    private  final Faker faker;
    private  final  CourseRepository courseRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    public SampleDataLoader(UserReponsitory userReponsitory, ChapterRepository chapterRepository, LessonRepository lessonRepository, Faker faker, CourseRepository courseRepository, PersonRepository personRepository, AddressRepository addressRepository) {
        this.userReponsitory = userReponsitory;
        this.chapterRepository = chapterRepository;
        this.lessonRepository = lessonRepository;
        this.faker = faker;
        this.courseRepository = courseRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        // create 10 rows of fake course data
//        List<User> userList = IntStream.rangeClosed(1,10)
//                .mapToObj(i->new User(
//                        faker.name().username(),
//                        "password",
//                        faker.name().lastName(),
//                        faker.internet().emailAddress(),
//                        "0867987223",
//                        faker.address().fullAddress(),
//                        faker.internet().image()
//                )).collect(Collectors.toList());
//        userReponsitory.saveAll(userList);
//        Chapter chapter = new Chapter();
//        chapter.setDescription("Chapter 1");
//        Lesson lesson = new Lesson();
//        lesson.setLessonTitle("Bai 1");
//        chapter.setLessons(Collections.singleton(lesson));
//        chapterRepository.saveAndFlush(chapter);
//        lessonRepository.findAll().forEach(p->{
//            System.out.println(p.getLessonTitle());
//        });
//
        List<Course> courses = IntStream.rangeClosed(1,10)
                .mapToObj(i-> new Course(
                        faker.book().title(),
                        new Random().nextInt(),
                        "https://images.pexels.com/photos/33109/fall-autumn-red-season.jpg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        new Random().nextBoolean(),
                        faker.programmingLanguage().name()
                )



                ).collect(Collectors.toList());
        courses.stream().forEach(
                course -> {
                    List<Chapter> chapters = IntStream.rangeClosed(1,3).mapToObj(j->new Chapter(
                            faker.dog().name())
                    ).collect(Collectors.toList());
                    course.setChapters(chapters);
                    courseRepository.saveAndFlush(course);
                }
        );
            Chapter chapter = new Chapter();
    chapter.setDescription("Day la khoa hoc tam bay");
    List<Lesson> lessons = IntStream.rangeClosed(1,3)
                    .mapToObj(i -> new Lesson("Phan cascascasca"))
                            .collect(Collectors.toList());

    chapter.setLessons(lessons);
    chapterRepository.saveAndFlush(chapter);


    }
}





