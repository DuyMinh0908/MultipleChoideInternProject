package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String contentQuestion;

    @Column(columnDefinition = "nvarchar(100)")
    @NotNull
    private String subject;

    @Column(columnDefinition = "varchar(10)")
    @NotNull
    private String level;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    List<Answer> answer;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private List<ExamQuestion> examQuestions;
}
