package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "exam_id")
    @NotNull
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

}
