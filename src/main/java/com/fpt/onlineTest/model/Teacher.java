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
    @NonNull
    @Column(columnDefinition = "varchar(20)")
    private String username;
    @NonNull
    @Column(columnDefinition = "varchar(16")
    private String userPass;
    @NonNull
    @Column(columnDefinition = "nvarchar(50)")
    private String fullName;
//
    private String email;
    @NonNull
    @Column(columnDefinition = "varchar(10")
    private String phone;
    @NonNull
    @Column(columnDefinition = "nvarchar(100)")
    private String address;
    @NonNull
    @Column(columnDefinition = "varchar(255")
    private String imageTeacher;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;

}
