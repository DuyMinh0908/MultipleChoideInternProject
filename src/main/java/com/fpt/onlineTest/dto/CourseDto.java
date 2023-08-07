package com.fpt.onlineTest.dto;

import com.fpt.onlineTest.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private Integer numberStudent;
    private String imageCourse;
    private Boolean status;
    private String subject;
    private TeacherDto teacher;
    private List<ChapterDto> chapters;
}
