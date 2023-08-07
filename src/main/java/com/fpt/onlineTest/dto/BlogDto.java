package com.fpt.onlineTest.dto;

import com.fpt.onlineTest.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Integer blogId;
    private String titleBlog;
    private String contentBlog;
    private String numberVisitors;
    private Integer userId;
    private String fullName;
    private String imageUser;

    // Constructors
//    public BlogDto(Integer blogId, String titleBlog, String contentBlog, String numberVisitors, Integer userId, String fullName, String imageUser) {
//        this.blogId = blogId;
//        this.titleBlog = titleBlog;
//        this.contentBlog = contentBlog;
//        this.numberVisitors = numberVisitors;
//        this.userId = userId;
//        this.fullName = fullName;
//        this.imageUser = imageUser;
//    }
}
