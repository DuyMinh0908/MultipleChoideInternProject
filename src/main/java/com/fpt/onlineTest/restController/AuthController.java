package com.fpt.onlineTest.restController;


import com.fpt.onlineTest.configuration.common.DuplicateEntryException;
import com.fpt.onlineTest.configuration.common.LoginDto;
import com.fpt.onlineTest.configuration.common.ResponseObject;
import com.fpt.onlineTest.configuration.common.UserDetailsDto;
import com.fpt.onlineTest.configuration.jwt.JwtProvider;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.AdminService;
import com.fpt.onlineTest.service.TeacherService;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/public")
public class AuthController {
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService service;
    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody() @Validated User dto) throws DuplicateEntryException {
        User user = new User();
        if(service.findByUsername(dto.getUsername()) != null) {
            throw new DuplicateEntryException("user", dto.getUsername() + " is already exits");
        }
        BeanUtils.copyProperties(dto, user);
        //confirm password
//        user.setStatus(Constant.USER_STATUS_ACTIVATED);
//        if (!dto.getUserPass().equals(dto.getPasswordConfirmation()))
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
//                    new ResponseObject(Constant.RESPONSE_STATUS_UNPROCESSABLE_ENTITY, "Password not same", ""));

        user.setUserPass(passwordEncoder.encode(user.getUserPass()));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Sucess", "", service.save(user)));
    }

    @PostMapping("user-login")
    public ResponseEntity<ResponseObject> adminLogin(@RequestBody LoginDto login){
        User user = service.findByUsername(login.getUsername());
        if(user != null){
            if (passwordEncoder.matches(login.getPassword(), user.getUserPass())){
                String token = jwtProvider.generateTokenLoginAdmin(login.getUsername());
                Map<String,Object> infor = new HashMap<>();
                infor.put("token",token);
                infor.put("user",user);
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Success",
                        "login  successfully",infor));
            }


            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject("ERROR", "password is incorrect", ""));
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                new ResponseObject("ERROR", "username not found", ""));

    }
}
