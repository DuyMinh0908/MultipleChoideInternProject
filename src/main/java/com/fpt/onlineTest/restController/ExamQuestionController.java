package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import com.fpt.onlineTest.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;

    @PostMapping("/exam/create-test/nums={nums}/subject={subject}/level={level}/exam-id={examId}")
    public ResponseEntity<List<ExamQuestion>> createExam(@PathVariable Integer nums, @PathVariable Integer examId, @PathVariable String subject, @PathVariable String level,@RequestBody Exam exam) {
        try {
            exam.setExamId(examId);
            return new ResponseEntity<>(examQuestionService.createExamTest(nums,exam,subject,level),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/exam/create-test/num={nums}/subject={subject}/exam-id={examId}")
    public ResponseEntity<List<ExamQuestion>> createExamWithRandomQuestions(@PathVariable Integer nums, @PathVariable Integer examId, @PathVariable String subject,Exam exam){
        try {
            exam.setExamId(examId);
            return new ResponseEntity<>(examQuestionService.createExamTestWithRandomQuestion(nums,exam,subject),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
