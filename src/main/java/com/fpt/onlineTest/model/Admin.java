package com.fpt.onlineTest.model;

import lombok.*;

import javax.persistence.*;
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
    private String imageAdmin;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

}
