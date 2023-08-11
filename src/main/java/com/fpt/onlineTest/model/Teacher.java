package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;
}
