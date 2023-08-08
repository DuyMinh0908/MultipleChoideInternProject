package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.CoursesRegistrationDto;
import com.fpt.onlineTest.model.CoursesRegistration;
import com.fpt.onlineTest.model.User;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CourseRegistrationService {
    // course registration
    CoursesRegistration courseRegistration(CoursesRegistration cr);

    //Cancel course registration
    void cancelRegistration(Integer crId);
    // get course user registration
    CoursesRegistrationDto getCourseUsers(Integer courseId, Pageable pageable, Optional<User> user);
}
