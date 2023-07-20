package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String courseName;
    private String nameTeacher;
    private Integer numberStudent;
    private String imageCourse;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

//    @JsonIgnore
//    @OneToMany(mappedBy = "courses")
//    List<User>users;

    @JsonIgnore
    @OneToMany(mappedBy = "courses")
    List<ContentCourse> contentCourses;

    @JsonIgnore
    @OneToMany(mappedBy = "courses")
    List<Questions>questions;

}
