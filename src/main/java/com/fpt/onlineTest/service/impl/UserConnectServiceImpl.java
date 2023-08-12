package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.AdminRepository;
import com.fpt.onlineTest.reponsitory.TeacherRepository;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.UserConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserConnectServiceImpl implements UserConnectService {

    @Autowired
    UserReponsitory userReponsitory;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<User> getAllUser() {
        return userReponsitory.findAll();
    }

    @Override
    public User login(String username, String password) {
        return userReponsitory.login(username, password);
    }

    @Override
    public Teacher loginTeacher(String username, String password) {
        return teacherRepository.login(username, password);
    }

    @Override
    public Admin loginAdmin(String username, String password) {
        return adminRepository.login(username, password);
    }


}
