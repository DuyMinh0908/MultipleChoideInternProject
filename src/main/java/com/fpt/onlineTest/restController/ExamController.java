package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/course/exam/add")
    public ResponseEntity<Exam> createExam(@RequestBody Exam newExam){
        try {
            return new ResponseEntity<>(examService.createExam(newExam), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/course/exam/update/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Integer id,@RequestBody Exam exam){
        try {
            return new ResponseEntity<>(examService.updateExam(id,exam), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/exam/{id}")
    public ResponseEntity<Optional<Exam>> getExamById(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(examService.getExamById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course={courseId}/exams")
    public ResponseEntity<List<Exam>> getExamByCourseId(@PathVariable Integer courseId){
        try {

            return new ResponseEntity<>(examService.getExamsByCourseId(courseId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/course/exam/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExamById(@PathVariable Integer id){
        try {
            examService.deleteExamBydId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/course={id}/exams/delete")
    public ResponseEntity<HttpStatus> deleteExamByCourseId(@PathVariable Integer id){
        try {
            examService.deleteExamByCourseId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
