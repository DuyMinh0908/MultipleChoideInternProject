package com.fpt.onlineTest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(columnDefinition = "varchar(20) ")
    @NotNull
    private String username;

    @Column(columnDefinition = "varchar(16)")
    @NotNull
    private String userPass;

    @Column(columnDefinition = "nvarchar(50) ")
    @NotNull
    private String fullName;

    @Column(columnDefinition = "varchar(100)")
    @NotNull
    private String email;

    @Column(columnDefinition = "varchar(10)")
    @NotNull
    private String phone;

    @Column(columnDefinition = "nvarchar(100) ")
    @NotNull
    private String address;

    @Column(columnDefinition = "varchar(255)")
    @NotNull
    private String imageAdmin;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

}
