package com.fpt.onlineTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserAnswers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAnswers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "answerId")
    Answer answer;
}
