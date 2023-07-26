package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(columnDefinition = "varchar(MAX)")
    @NonNull
    private String courseName;

    @Column(columnDefinition = "varchar(10)")
    @NonNull
    private Integer numberStudent;

    @Column(columnDefinition = "varchar(MAX)")
    @NonNull
    private String imageCourse;

    @Column(columnDefinition = "varchar(100)")
    @NonNull
    private Boolean status;

    @Column(columnDefinition = "varchar(20)")
    @NonNull
    private String subject;

    @JsonIgnore
    @OneToMany(mappedBy = "courses")
    List<ContentCourse> contentCourses;

    @JsonIgnore
    @OneToMany(mappedBy = "courses")
    List<Exam> exams;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "courses_registration",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

}
