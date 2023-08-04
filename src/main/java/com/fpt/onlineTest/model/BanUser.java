package com.fpt.onlineTest.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BanUser")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class BanUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer banUserId;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "examId")
    Exam exam;
}
