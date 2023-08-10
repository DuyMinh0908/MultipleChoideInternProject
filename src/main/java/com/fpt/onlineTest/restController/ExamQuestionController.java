package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import com.fpt.onlineTest.reponsitory.ExamQuestionRepository;
import com.fpt.onlineTest.reponsitory.ExamRepository;
import com.fpt.onlineTest.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ExamQuestionController {
    @Autowired
    private ExamQuestionService examQuestionService;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @PostMapping("/exam/create-test/nums={nums}/subject={subject}/level={level}/exam-id={examId}")
    public ResponseEntity<List<ExamQuestion>> createExam(@PathVariable Integer nums, @PathVariable Integer examId, @PathVariable String subject, @PathVariable String level, @RequestBody Exam exam) {
        try {
            exam.setExamId(examId);
            Integer qAmount = examRepository.getExamQuestionAmount(examId);
            if (Objects.equals(nums, qAmount))
                return new ResponseEntity<>(examQuestionService.createExamTest(nums, exam, subject, level), HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/exam/create-test/num={nums}/subject={subject}/exam-id={examId}")
    public ResponseEntity<List<ExamQuestion>> createExamWithRandomQuestions(@PathVariable Integer nums, @PathVariable Integer examId, @PathVariable String subject) {
        try {
//            exam.setExamId(examId);
            Integer qAmount = examRepository.getExamQuestionAmount(examId);
            if (Objects.equals(nums, qAmount)) {
                return new ResponseEntity<>(examQuestionService.createExamTestWithRandomQuestion(nums, examId, subject), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/exam/question/add")
    public ResponseEntity<ExamQuestion> addSingleQuestionToExam(@RequestBody ExamQuestion examQuestion) {
        ExamQuestion exitingExamQuestion = examQuestionRepository.findQuestionExist(examQuestion.getExam().getExamId(), examQuestion.getQuestion().getQuestionId());
        try {
            if (exitingExamQuestion == null)
                return new ResponseEntity<>(examQuestionService.addSingleQuestion(examQuestion), HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exam/questions/exam-id={id}")
    public ResponseEntity<Page<ExamQuestion>> getExamTestQuestions(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(examQuestionService.getExamTestQuestions(id, pageable), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/exam/question/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExamQuestionById(@PathVariable Integer id) {
        try {
            examQuestionService.deleteQuestionById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/exam/questions/delete/exam-id={id}")
    public ResponseEntity<HttpStatus> deleteExamAllQuestionByExamId(@PathVariable Integer id) {
        try {
            examQuestionService.deleteExamAllQuestion(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/exam/questions/delete-selected/")
    public ResponseEntity<HttpStatus> deleteListOfQuestion(@RequestBody List<Integer> id) {
        try {
            examQuestionService.deleteListOfQuestion(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
