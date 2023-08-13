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

@RestController
@CrossOrigin("*")
    @RequestMapping("/api/v1/user-connect")
public class UserConnectController {

    @Autowired
    UserConnectService userConnectService;

    @GetMapping("/user/list")
    public ResponseEntity<ResponseObject> getAllUser() {
        try{
            List<User> users = userConnectService.getAllUser();
            return new ResponseEntity<>(new ResponseObject("success", "Get successfully!!", users), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseObject("failed", "Error!!", ""), HttpStatus.NOT_FOUND);
        }
    }

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

}
