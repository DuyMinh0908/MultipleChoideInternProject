package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String courseName;

    @Column
    @NotNull
    private Integer numberStudent;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String imageCourse;

    @Column
    @NotNull
    private Boolean status;

    @Column(columnDefinition = "varchar(20)")
    @NotNull
    private String subject;

    @JsonIgnore
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    List<Chapter> contentCours;

    @JsonIgnore
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    List<Exam> exams;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "courses_registration",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    public Course(String courseName, String imageCourse, Integer numberStudent, Boolean status, String subject) {
        this.courseName = courseName;
        this.numberStudent = numberStudent;
        this.imageCourse = imageCourse;
        this.status = status;
        this.subject = subject;
    }
    public Course(Integer id, String courseName, String imageCourse, Integer numberStudent, Boolean status, String subject) {
        this.courseId = id;
        this.courseName = courseName;
        this.numberStudent = numberStudent;
        this.imageCourse = imageCourse;
        this.status = status;
        this.subject = subject;
    }
}
