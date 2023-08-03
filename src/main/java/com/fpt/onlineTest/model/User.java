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

    public User(String username, String userPass, String fullName, String email, String phone, String address, String imageUser) {
        this.username = username;
        this.userPass = userPass;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.imageUser = imageUser;
    }

    @Column(columnDefinition = "varchar(10)not null")
    private String phone;

    @Column(columnDefinition = "nvarchar(100) not null")
    private String address;

    @Column(columnDefinition = "varchar(255)not null")
    private String imageUser;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

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
