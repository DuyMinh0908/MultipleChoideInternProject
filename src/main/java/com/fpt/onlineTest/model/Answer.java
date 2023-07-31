package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Answers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    private String contentAnswer;

    @ManyToOne
    @JoinColumn(name = "questionId")
    Questions question;

    @JsonIgnore
    @OneToMany(mappedBy = "answer")
    List<ResultQuestion> resultQuestions;

    @JsonIgnore
    @OneToMany(mappedBy = "answer")
    List<UserAnswers> userAnswers;

}
