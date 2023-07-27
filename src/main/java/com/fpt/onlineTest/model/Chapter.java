package com.fpt.onlineTest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ContentCourses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contentCourseId;
    private String description;

    @ManyToOne
    @JoinColumn(name = "courseId")
    Course courses;
}
