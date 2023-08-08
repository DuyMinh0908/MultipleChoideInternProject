package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.CoursesRegistrationDto;
import com.fpt.onlineTest.model.CoursesRegistration;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.CoursesRegistrationRepository;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    @Autowired
    private CoursesRegistrationRepository coursesRegistrationRepository;
    @Autowired
    private UserReponsitory userReponsitory;


    @Override
    public CoursesRegistration courseRegistration(CoursesRegistration cr) {
        return coursesRegistrationRepository.save(cr);
    }

    @Override
    public void cancelRegistration(Integer crId) {
        coursesRegistrationRepository.deleteById(crId);
    }

    @Override
    public CoursesRegistrationDto getCourseUsers(Integer courseId, Pageable pageable, Optional<User> user) {
        Page<User> userPage = userReponsitory.findCourseSUsers(courseId, pageable);
        return null;
    }
}
