package com.fpt.onlineTest.restController;


import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.TeacherService;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TeacherService teacherService;

    //--------------Get All-------------------
    @GetMapping("users/getAll")
    public List<User> getAll(){
        return userService.findAll();
    }
    //--------------Create User-------------------
    @PostMapping("users/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User addUser = userService.save(user);
        return ResponseEntity.ok(addUser);
    }
    //--------------Update User-------------------

    @PutMapping("users/updateUser/{userId}")
    public User update(@PathVariable("userId") Integer userId, @RequestBody User user){
        return userService.saveAndFlush(user);
    }

    //--------------Delete User-------------------
    @DeleteMapping("users/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId")Integer userId ){
        boolean delete = userService.deleteById(userId);
        if(delete){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }
//
//    @PutMapping("/users/updateUserToTeacher/{userId}")
//    public ResponseEntity<String> updateUserToTeacher(@PathVariable("userId") Integer userId) {
//        Optional<User> existingUserOptional = userService.findById(userId);
//
//        if (!existingUserOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        User existingUser = existingUserOptional.get();
//
//        Role role = existingUser.getRole();
//
//        if (!role.getUsers().contains("USER")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a regular user");
//        }
//
//        role.getUsers().remove("USER");
//        role.getUsers().add("TEACHER");
//
//        Teacher teacher = new Teacher(existingUser); // Tạo một đối tượng Teacher từ thông tin của người dùng
//
//        userService.save(existingUser);
//        teacherService.save(teacher);
//
//        return ResponseEntity.ok("User role updated to teacher");
//    }


}

