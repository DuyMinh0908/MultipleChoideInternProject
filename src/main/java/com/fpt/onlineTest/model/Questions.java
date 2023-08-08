package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Questions")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String contentQuestion;

    @JsonIgnore
    @Column(columnDefinition = "nvarchar(100)")
    @NotNull
    private String subject;

    @JsonIgnore
    @Column(columnDefinition = "varchar(10)")
    @NotNull
    private String level;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Answer> answer;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private List<ExamQuestion> examQuestions;
}
