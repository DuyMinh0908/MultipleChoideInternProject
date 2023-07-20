package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Questions")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    private String contentQuestion;

    @ManyToOne
    @JoinColumn(name = "examId")
    Exam exam;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    List<Answer> answer;

    @ManyToOne
    @JoinColumn(name = "courseId")
    Course courses;

}
