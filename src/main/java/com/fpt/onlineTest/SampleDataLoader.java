//package com.fpt.onlineTest;
//
//import com.fpt.onlineTest.model.*;
//import com.fpt.onlineTest.reponsitory.*;
//import com.github.javafaker.Faker;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class SampleDataLoader implements CommandLineRunner {
//    private final RoleRepository roleRepository;
//    private final UserReponsitory userRepository;
//    private final TeacherRepository teacherRepository;
//    private final CourseRepository courseRepository;
//    private final ChapterRepository chapterRepository;
//    private final LessonRepository lessonRepository;
//    private final AnswerRepository answerRepository;
//    private final QuestionRepository questionRepository;
//    private final ExamRepository examRepository;
//    private final CoursesRegistrationRepository coursesRegistrationRepository;
//    private final ExamQuestionRepository examQuestionRepository;
//    private final UserAnswersRepository userAnswersRepository;
//    private final ResultExamRepository resultExamRepository;
//    private final ResultQuestionRepository resultQuestionRepository;
//    private final Faker faker;
//
//    public SampleDataLoader(UserReponsitory userRepository1, CourseRepository courseRepository, ChapterRepository chapterRepository,
//                            LessonRepository lessonRepository, TeacherRepository teacherRepository,
//                            RoleRepository roleRepository, AnswerRepository answerRepository, QuestionRepository questionRepository, ExamRepository examRepository, CoursesRegistrationRepository coursesRegistrationRepository, ExamQuestionRepository examQuestionRepository, UserAnswersRepository userAnswersRepository, ResultExamRepository resultExamRepository, ResultQuestionRepository resultQuestionRepository, Faker faker) {
//        this.userRepository = userRepository1;
//        this.courseRepository = courseRepository;
//        this.chapterRepository = chapterRepository;
//        this.lessonRepository = lessonRepository;
//        this.teacherRepository = teacherRepository;
//        this.roleRepository = roleRepository;
//        this.answerRepository = answerRepository;
//        this.questionRepository = questionRepository;
//        this.examRepository = examRepository;
//        this.coursesRegistrationRepository = coursesRegistrationRepository;
//        this.examQuestionRepository = examQuestionRepository;
//        this.userAnswersRepository = userAnswersRepository;
//        this.resultExamRepository = resultExamRepository;
//        this.resultQuestionRepository = resultQuestionRepository;
//        this.faker = faker;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Tạo dữ liệu giả mạo cho bảng Role
//        Role studentRole = new Role("Student");
//        Role teacherRole = new Role("Teacher");
//        Role adminRole = new Role("Admin");
//        roleRepository.save(studentRole);
//        roleRepository.save(teacherRole);
//        roleRepository.save(adminRole);
//
//        // Tạo dữ liệu giả mạo cho bảng User
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setUsername(faker.name().username());
//            user.setUserPass(faker.internet().password());
//            user.setFullName(faker.name().fullName());
//            user.setEmail(faker.internet().emailAddress());
//            user.setPhone(faker.phoneNumber().phoneNumber());
//            user.setAddress(faker.address().fullAddress());
//            user.setImageUser(faker.internet().image());
//            user.setRole(studentRole); // Gán vai trò "Student" cho user
//            userRepository.save(user);
//            users.add(user);
//        }
//
//        // Tạo dữ liệu giả mạo cho bảng Teacher
//        List<Teacher> teachers = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Teacher teacher = new Teacher();
//            teacher.setUsername(faker.name().username());
//            teacher.setUserPass(faker.internet().password());
//            teacher.setFullName(faker.name().fullName());
//            teacher.setEmail(faker.internet().emailAddress());
//            teacher.setPhone(faker.phoneNumber().phoneNumber());
//            teacher.setAddress(faker.address().fullAddress());
//            teacher.setImageTeacher(faker.internet().image());
//            teacher.setRole(teacherRole); // Gán vai trò "Teacher" cho teacher
//            teacherRepository.save(teacher);
//            teachers.add(teacher);
//        }
//
//        // Tạo dữ liệu giả mạo cho bảng Course, Chapter và Lesson
//        List<Course> courses = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Course course = new Course();
//            course.setCourseName(faker.educator().course());
//            course.setNumberStudent(faker.number().numberBetween(10, 100));
//            course.setImageCourse(faker.internet().image());
//            course.setStatus(faker.random().nextBoolean());
//            course.setSubject(faker.educator().course());
//            courseRepository.save(course);
//            courses.add(course);
//
//            for (int j = 0; j < 5; j++) {
//                Chapter chapter = new Chapter();
//                chapter.setDescription(faker.lorem().sentence());
//                chapter.setCourses(course);
//                chapterRepository.save(chapter);
//
//                for (int k = 0; k < 3; k++) {
//                    Lesson lesson = new Lesson();
//                    lesson.setLessonTitle(faker.educator().course());
//                    lesson.setChapter(chapter);
//                    lessonRepository.save(lesson);
//                }
//            }
//        }
//
//        // Tạo dữ liệu giả mạo cho bảng Answer và Questions
//        List<Answer> answers = new ArrayList<>();
//        List<Questions> questions = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Questions question = new Questions();
//            question.setContentQuestion(faker.lorem().sentence());
//            question.setSubject(faker.educator().course());
//            question.setLevel(faker.random().nextInt(1, 5) + "hard");
//            questionRepository.save(question);
//            questions.add(question);
//
//            for (int j = 0; j < 4; j++) {
//                Answer answer = new Answer();
//                answer.setContentAnswer(faker.lorem().sentence());
//                answer.setQuestion(question);
//                answerRepository.save(answer);
//                answers.add(answer);
//            }
//        }
//
//        // Tạo dữ liệu giả mạo cho bảng Exam
//        for (Course course : courses) {
//            Exam exam = new Exam();
//            exam.setNumQuestion(faker.number().numberBetween(5, 20));
//            exam.setTimeStart(LocalDateTime.now());
//            exam.setTimeEnd(LocalDateTime.now().plusHours(2));
//            exam.setCourses(course);
//            examRepository.save(exam);
//
//            // Tạo dữ liệu giả mạo cho bảng ExamQuestion
//            for (int i = 0; i < exam.getNumQuestion(); i++) {
//                Questions question = questions.get(faker.number().numberBetween(0, questions.size()));
//                ExamQuestion examQuestion = new ExamQuestion(question, exam);
//                examQuestionRepository.save(examQuestion);
//            }
//        }
//
//        // Tạo dữ liệu giả mạo cho bảng CoursesRegistration
//        for (User user : users) {
//            Course course = courses.get(faker.number().numberBetween(0, courses.size()));
//            Teacher teacher = teachers.get(faker.number().numberBetween(0, teachers.size()));
//            CoursesRegistration coursesRegistration = new CoursesRegistration(user, course, teacher);
//            coursesRegistrationRepository.save(coursesRegistration);
//        }
//
//
//
//
//    }
//}
