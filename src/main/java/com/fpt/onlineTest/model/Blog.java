package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Blogs")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String titleBlog;
    @Column(columnDefinition = "nvarchar(MAX)")
    private String contentBlog;

    private String numberVisitors;


   @ManyToOne
   @JoinColumn(name = "userId")
   User user;


}