package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.CoursesRegistrationDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.CoursesRegistration;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.CoursesRegistrationRepository;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    //
    @Override
    public CoursesRegistrationDto getCourseUsers(Integer courseId, Pageable pageable) {
        List<Integer> crUserIdList = coursesRegistrationRepository.findUserIdsByCourseId(courseId);
        System.out.println("crUserIdList"+crUserIdList);
        Page<User> userPage = userReponsitory.findCourseSUsers(crUserIdList, pageable);
        System.out.println("crDto getCourseUsers: " + userPage);
        CoursesRegistrationDto crDto = new CoursesRegistrationDto();
        crDto.setUserDto(userPage.map(this::mapUserToDto));
        return crDto;
    }
    public CoursesRegistrationDto getCourseUsers(Integer courseId){
        List<Integer> crUserIdList = coursesRegistrationRepository.findUserIdsByCourseId(courseId);
        System.out.println("crUserIdList: "+crUserIdList);
        return new CoursesRegistrationDto();
    }
    public UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setImageUser(user.getImageUser());
        return userDto;
    }
}
