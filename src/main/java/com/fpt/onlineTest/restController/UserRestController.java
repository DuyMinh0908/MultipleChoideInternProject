package com.fpt.onlineTest.restController;


import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
//import com.fpt.onlineTest.service.TeacherService;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    UserService userService;
//
//    @Autowired
//    TeacherService teacherService;

    //--------------Get All-------------------
    @GetMapping("users/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    //--------------Create User-------------------
    @PostMapping("users/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User addUser = userService.save(user);
        return ResponseEntity.ok(addUser);
    }
    //--------------Update User-------------------

    @PutMapping("users/updateUser/{userId}")
    public User update(@PathVariable("userId") Integer userId, @RequestBody User user) {
        return userService.saveAndFlush(user);
    }

    //--------------Delete User-------------------
    @DeleteMapping("users/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {
        boolean delete = userService.deleteById(userId);
        if (delete) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //    ------------ hoviet -------------------//
//
}

