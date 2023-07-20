package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(columnDefinition = "nvarchar(100) not null")
    private String username;
    private String userPass;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String imageUser;
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Answer> answers;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<ResultExam> resultExams;

}
