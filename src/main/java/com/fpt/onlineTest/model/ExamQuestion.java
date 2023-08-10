package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ExamQuestion")
public class ExamQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public ExamQuestion(Questions question, Exam exam) {
        this.question = question;
        this.exam = exam;
    }

}
