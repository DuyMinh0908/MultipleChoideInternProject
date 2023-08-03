package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.UserAnswers;
import com.fpt.onlineTest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-answers")
public class UserAnswerController {
    @Autowired
    UserAnswerService userAnswerService;

    @GetMapping("")
    public ResponseEntity<List<UserAnswers>> getAllUserAnswer() {
        try{
            return new ResponseEntity<>(userAnswerService.getAllUserAnswers(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<UserAnswers>> getUserAnswerByUserId(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(userAnswerService.getUserAnswerByUserId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserAnswers> newUserAnswer(@RequestBody UserAnswers newUserAnswers) {
        try {
            return new ResponseEntity<>(userAnswerService.createUserAnswer(newUserAnswers), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
