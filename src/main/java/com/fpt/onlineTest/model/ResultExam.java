package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ResultExams")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultExamId;
    private Double point;

    @JsonIgnore
    @OneToMany(mappedBy = "resultExam")
    List<Exam> exams;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

}
