package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "Role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role implements Serializable {
    @Id //Đánh dấu là primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(columnDefinition = "varchar(20) not null")
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Teacher> teachers;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<Admin> admins;
}