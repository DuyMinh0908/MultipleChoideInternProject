package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ResultExams")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class ResultExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultExamId;

    @Column(columnDefinition = "varchar(100)")
    @NotNull
    private Double point;

    @OneToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnoreProperties({"numQuestion", "timeStart", "timeEnd", "courses"})
    @NotNull
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"userPass", "fullName", "email", "phone", "address", "imageUser", "role"})
    User user;

}
