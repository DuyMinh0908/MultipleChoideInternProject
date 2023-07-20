package com.fpt.onlineTest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ResultQuestions")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;


    @ManyToOne
    @JoinColumn(name = "answerId")
    Answer answer;


}
