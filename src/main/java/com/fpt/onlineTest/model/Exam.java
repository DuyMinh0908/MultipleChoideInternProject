package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Integer numQuestion;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;

    @ManyToOne
    @JoinColumn(name = "resultExamId")
    ResultExam resultExam;



    @JsonIgnore
    @OneToMany(mappedBy = "exam")
    List<Questions>questions;



}
