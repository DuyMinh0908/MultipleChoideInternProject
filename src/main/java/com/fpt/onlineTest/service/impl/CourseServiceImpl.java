package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.ChapterDto;
import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.dto.LessonDto;
import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Lesson;
import com.fpt.onlineTest.reponsitory.ChapterRepository;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.LessonRepository;
import com.fpt.onlineTest.reponsitory.TeacherRepository;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private LessonRepository lessonRepository;

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
        }
        return null;
    }

    @Override
    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Page<Course> getCoursesByTeacherId(Integer teacherId, Pageable pageable) {
        return courseRepository.findCoursesByTeacherId(teacherId, pageable);
    }

    @Override
    public Page<Course> getCoursesByStudentId(Integer studentId, Pageable pageable) {
        return courseRepository.findCoursesByStudentId(studentId, pageable);
//        return null;
    }


    @Override
    public List<Course> getPopuLarCourses() {
        List<Course> topPopularCourses = courseRepository.findTopPopularCourse();
        return topPopularCourses.subList(0, Math.min(5, topPopularCourses.size()));
    }

    @Override
    public Optional<CourseDto> getCourseDtoById(Integer courseId) {
        return courseRepository.findById(courseId).map(this::mapToDTO);
    }

    @Override
    public Optional<Course> getCourseById(Integer courseId) {
        return courseRepository.findById(courseId);
    }

    private CourseDto mapToDTO(Course course) {
        CourseDto courseDTO = new CourseDto();
        // Map fields from Course entity to CourseDTO
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setNumberStudent(course.getNumberStudent());
        courseDTO.setImageCourse(course.getImageCourse());
        courseDTO.setStatus(course.getStatus());
        courseDTO.setSubject(course.getSubject());

        List<ChapterDto> chapterDtoList = new ArrayList<>();
        List<Chapter> chapterList = chapterRepository.findCourseSChapter(courseDTO.getCourseId());
        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            chapterDto.setChapterId(chapter.getChapterId());
            chapterDto.setDescription(chapter.getDescription());

            List<LessonDto> lessonDtoList = new ArrayList<>();
            List<Lesson> lessonList = lessonRepository.findLessonsByChapterId(chapterDto.getChapterId());
            for (Lesson lesson : lessonList) {
                LessonDto lessonDto = new LessonDto();
                lessonDto.setLessonId(lesson.getLessonId());
                lessonDto.setLessonTitle(lesson.getLessonTitle());
                lessonDtoList.add(lessonDto);
            }
            chapterDto.setLessons(lessonDtoList);
            chapterDtoList.add(chapterDto);
            courseDTO.setChapters(chapterDtoList);
        }
        courseDTO.setChapters(chapterDtoList);
        return courseDTO;
    }

    //  get teacher id
    Integer getTeacherId() {
        return null;
    }
}
