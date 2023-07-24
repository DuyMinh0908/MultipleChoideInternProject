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
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(columnDefinition = "varchar(20) not null")
    private String username;
    @Column(columnDefinition = "varchar(16)not null")
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
//    @Column(columnDefinition = "varchar(10)not null")
//    private String role;
//
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "courses_registration",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Answer> answers;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<ResultExam> resultExams;

    @OneToMany(mappedBy = "user")
    List<Blog> blogs;

}
