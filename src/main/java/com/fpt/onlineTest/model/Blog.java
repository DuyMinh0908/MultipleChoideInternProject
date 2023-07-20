package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Blogs")
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    private String titleBlog;
    private String contentBlog;
    private String numberVisitors;


   @ManyToOne
   @JoinColumn(name = "userId")
   User user;


}
