package com.fpt.onlineTest.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserAnswers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserAnswers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "answerId")
    Answer answer;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;
}
