package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;

    @PostMapping("/exam/create-questions/nums={nums}/subject={subject}/level={level}/examId={examId}")
    public ResponseEntity<HttpStatus> createExam(@PathVariable Integer nums, @PathVariable Integer examId,@PathVariable String subject,@PathVariable String level) {
        try {
            Exam exam = new Exam();
            exam.setExamId(examId);
            examQuestionService.createExamTest(nums,exam,subject,level);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
