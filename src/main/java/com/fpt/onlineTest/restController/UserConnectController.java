package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.UserConnectDto;
import com.fpt.onlineTest.model.ResponseObject;
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

            return new ResponseEntity<>(new ResponseObject("success", "Login successfully!!", user), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", "Username or password is incorrect!!", ""), HttpStatus.NOT_FOUND);
        }
    }

}
