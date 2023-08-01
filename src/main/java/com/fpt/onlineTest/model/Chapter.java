package com.fpt.onlineTest.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Chapters")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chapterId;

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "courseId")
    Course courses;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    List<Lesson> lessons;
}
