package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserConnectService {

    //User
    List<User> getAllUser();

    User login(String username, String password);

    Optional<User> getUserById(Integer userId);

    void createUser(User user);

    User checkUsername(String username);

    User checkEmail(String email);

    void deleteUser(Integer userId);

    //Teacher
    List<Teacher> getAllTeacher();

    Teacher loginTeacher(String username, String password);

    Optional<Teacher> getTeacherById(Integer teacherId);

    Teacher createTeacher(Teacher teacher);

    Teacher checkUserNameForTeacher(String username);

    Teacher checkEmailForTeacher(String email);

    void deleteTeacher(Integer teacherId);

    //Admin
    Admin loginAdmin(String username, String password);

}
