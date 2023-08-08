package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String lessonTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapterId")
    private Chapter chapter;

    public Lesson(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
}
