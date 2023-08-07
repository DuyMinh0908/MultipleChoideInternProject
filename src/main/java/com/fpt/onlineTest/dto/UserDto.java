package com.fpt.onlineTest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.CoursesRegistration;
import com.fpt.onlineTest.model.ResultExam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String username;
    private String userPass;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String imageUser;
    private Page<BlogDto> blogs;
}
