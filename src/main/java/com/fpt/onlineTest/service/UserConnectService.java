package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;

import java.util.List;

public interface UserConnectService {
    List<User> getAllUser();

    User login(String username, String password);

    Teacher loginTeacher(String username, String password);

    Admin loginAdmin(String username, String password);

}
