package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.UserConnectDto;
import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.ResponseObject;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.UserConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user-connect")
public class UserConnectController {

    @Autowired
    UserConnectService userConnectService;

    @PostMapping("login")
    public ResponseEntity<ResponseObject> login(@RequestParam("username") String username,
                                                @RequestParam("password") String password) {
        try{
            User user = userConnectService.login(username.trim(), password.trim());
            Teacher teacher = userConnectService.loginTeacher(username.trim(), password.trim());
            Admin admin = userConnectService.loginAdmin(username.trim(), password.trim());
            UserConnectDto userConnectDto = new UserConnectDto();
            if(user == null) {
                if(teacher == null) {
                    userConnectDto.setId(admin.getAdminId());
                    userConnectDto.setAddress(" ");
                    userConnectDto.setEmail(" ");
                    userConnectDto.setFullName(" ");
                    userConnectDto.setImage(" ");
                    userConnectDto.setPhone(" ");
                    userConnectDto.setUsername(admin.getUsername());
                    userConnectDto.setPassword(admin.getUserPass());
                    userConnectDto.setRole(admin.getRole().getRoleId());
                }else{
                    userConnectDto.setId(teacher.getId());
                    userConnectDto.setAddress(teacher.getAddress());
                    userConnectDto.setEmail(teacher.getEmail());
                    userConnectDto.setFullName(teacher.getFullName());
                    userConnectDto.setImage(teacher.getImageTeacher());
                    userConnectDto.setPhone(teacher.getPhone());
                    userConnectDto.setUsername(teacher.getUsername());
                    userConnectDto.setPassword(teacher.getUserPass());
                    userConnectDto.setRole(teacher.getRole().getRoleId());
                }
            }else{
                userConnectDto.setId(user.getUserId());
                userConnectDto.setAddress(user.getAddress());
                userConnectDto.setEmail(user.getEmail());
                userConnectDto.setFullName(user.getFullName());
                userConnectDto.setImage(user.getImageUser());
                userConnectDto.setPhone(user.getPhone());
                userConnectDto.setUsername(user.getUsername());
                userConnectDto.setPassword(user.getUserPass());
                userConnectDto.setRole(user.getRole().getRoleId());
            }
            return new ResponseEntity<>(new ResponseObject("success", "Login successfully!!", userConnectDto), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", "Username or password is incorrect!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    //User
    @GetMapping("/user/list")
    public ResponseEntity<ResponseObject> getAllUser() {
        try{
            List<User> users = userConnectService.getAllUser();
            return new ResponseEntity<>(new ResponseObject("success", "Get all user successfully!!", users), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseObject("failed", "Error!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Integer id) {
        try{
            Optional<User> user = userConnectService.getUserById(id);
            return new ResponseEntity<>(new ResponseObject("success", "Get user by id successfully!!", user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", "Id not found!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/create")
    public ResponseEntity<ResponseObject> createUser(@RequestBody User newUser) {
        try{
            User user = userConnectService.createUser(newUser);
            return new ResponseEntity<>(new ResponseObject("success", "Create user successfully!!", user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", "Create user failed!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/check-username")
    public ResponseEntity<ResponseObject> checkUserName(@RequestParam("username") String username){
        try{
            User user = userConnectService.checkUsername(username);
            boolean isChecked;
            if(user == null){
                isChecked = true;
            }else {
                isChecked = false;
            }
            return new ResponseEntity<>(new ResponseObject("success", "Check user successfully!!", isChecked), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/check-email")
    public ResponseEntity<ResponseObject> checkEmail(@RequestParam("email") String email){
        try{
            User user = userConnectService.checkEmail(email);
            boolean isChecked;
            if(user == null){
                isChecked = true;
            }else {
                isChecked = false;
            }
            return new ResponseEntity<>(new ResponseObject("success", "Check user successfully!!", isChecked), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
    }

    //Teacher
    @GetMapping("/teacher/list")
    public ResponseEntity<ResponseObject> getAllTeacher() {
        try{
            List<Teacher> teachers = userConnectService.getAllTeacher();
            return new ResponseEntity<>(new ResponseObject("success", "Get all user successfully!!", teachers), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseObject("failed", "Error!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<ResponseObject> getTeacherById(@PathVariable Integer id) {
        try{
            Optional<Teacher> teacher = userConnectService.getTeacherById(id);
            return new ResponseEntity<>(new ResponseObject("success", "Get teacher by id successfully!!", teacher), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", "Id not found!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teacher/create")
    public ResponseEntity<ResponseObject> createTeacher(@RequestBody Teacher newTeacher) {
        try{
            Teacher teacher = userConnectService.createTeacher(newTeacher);
            return new ResponseEntity<>(new ResponseObject("success", "Create user successfully!!", teacher), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", "Create user failed!!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teacher/check-username")
    public ResponseEntity<ResponseObject> checkUserNameForTeacher(@RequestParam("username") String username){
        try{
            Teacher teacher = userConnectService.checkUserNameForTeacher(username);
            boolean isChecked;
            if(teacher == null){
                isChecked = true;
            }else {
                isChecked = false;
            }
            return new ResponseEntity<>(new ResponseObject("success", "Check username successfully!!", isChecked), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teacher/check-email")
    public ResponseEntity<ResponseObject> checkEmailForTeacher(@RequestParam("email") String email){
        try{
            Teacher teacher = userConnectService.checkEmailForTeacher(email);
            boolean isChecked;
            if(teacher == null){
                isChecked = true;
            }else {
                isChecked = false;
            }
            return new ResponseEntity<>(new ResponseObject("success", "Check username successfully!!", isChecked), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
    }
}
