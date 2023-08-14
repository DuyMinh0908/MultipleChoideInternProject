package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(columnDefinition = "varchar(20) not null")
    private String username;

    @Column(columnDefinition = "varchar(MAX) not null")
    private String userPass;

    @Column(columnDefinition = "nvarchar(50) not null")
    private String fullName;

    private String email;

    @Column(columnDefinition = "varchar(10)not null")
    private String phone;

    @Column(columnDefinition = "nvarchar(100) not null")
    private String address;

    @Column(columnDefinition = "varchar(255)not null")
    private String imageUser;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @Column(name = "status", columnDefinition = "varchar(255) not null")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CoursesRegistration> courseRegistrations;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResultExam> resultExams;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Blog> blogs;
}
