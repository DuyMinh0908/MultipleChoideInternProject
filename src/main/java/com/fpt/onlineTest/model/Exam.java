package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Exams")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examId;

    @NotNull
    private Integer numQuestion;

    @NotNull
    private LocalDateTime timeStart;

    @NotNull
    private LocalDateTime timeEnd;

//    @ManyToOne
//    @JoinColumn(name = "resultExamId")
//    ResultExam resultExam;

    @JsonIgnoreProperties({"courseName","numberStudent","imageCourse","status","subject","teacher"})
    @ManyToOne
    @JoinColumn(name = "courseId")
    Course courses;

    @JsonIgnore
    @OneToMany(mappedBy = "exam")
    private List<ExamQuestion> examQuestions;

    public Exam(Integer questionId) {
    }
}
