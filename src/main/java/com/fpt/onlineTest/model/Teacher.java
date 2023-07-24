package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    @Column(columnDefinition = "varchar(20) not null")
    private String username;
    @Column(columnDefinition = "varchar(16)not null")
    private String userPass;
    @Column(columnDefinition = "nvarchar(50) not null")
    private String fullName;
//
    private String email;
    @Column(columnDefinition = "varchar(10)not null")
    private String phone;
    @Column(columnDefinition = "nvarchar(100) not null")
    private String address;
    @Column(columnDefinition = "varchar(255)not null")
    private String imageTeacher;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;

}
