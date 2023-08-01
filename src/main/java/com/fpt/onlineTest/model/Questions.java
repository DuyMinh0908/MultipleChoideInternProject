package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String contentQuestion;

    @Column(columnDefinition = "varchar(10)")
    @NotNull
    private String level;

    @Column(columnDefinition = "varchar(100)")
    @NotNull
    private String subject;

//    @JsonIgnore
//    @OneToMany(mappedBy = "question")
//    List<Answer> answer;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "exam_questions",
//            joinColumns = @JoinColumn(name = "question_id"),
//            inverseJoinColumns = @JoinColumn(name = "exam_id"))
//    private List<Exam> exams;
}
