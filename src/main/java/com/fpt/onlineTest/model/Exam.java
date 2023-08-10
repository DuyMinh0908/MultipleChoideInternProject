package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Exams")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer examId;

    @NotNull
    private Integer numQuestion;

    //    @Column(columnDefinition = "time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @NotNull
    private LocalTime duration; // (hh:mm:ss)

//    @ManyToOne
//    @JoinColumn(name = "resultExamId")
//    ResultExam resultExam;

    @JsonIgnoreProperties({"courseName", "numberStudent", "imageCourse", "status", "subject", "teacher"})
    @ManyToOne
    @JoinColumn(name = "courseId")
    Course courses;

    @JsonIgnore
    @OneToMany(mappedBy = "exam")
    private List<ExamQuestion> examQuestions;

//    public String getFormattedDuration() {
//        return duration.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//    }

    public Exam(Integer questionId) {
    }
}
