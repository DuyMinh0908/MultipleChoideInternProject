package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //    create chapter
    @PostMapping("/course/chapters/create")
    public ResponseEntity<Chapter> createNewChapter(@RequestBody Chapter newChapter) {
        try {
            return new ResponseEntity<>(chapterService.createChapter(newChapter), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get course chapter
    @GetMapping("/course/chapter/{chapterId}")
    public ResponseEntity<Optional<Chapter>> getChapter(@PathVariable Integer chapterId) {
        try {
            return new ResponseEntity<>(chapterService.getCourseChapter(chapterId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    get course all chapter
    @GetMapping("/course/chapters")
    public ResponseEntity<List<Chapter>> getCourseAllChapters() {
        try {
            return new ResponseEntity<>(chapterService.getAllCourseChapters(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/course/chapters/delete-chapter/{id}")
    public ResponseEntity<HttpStatus> deleteChapter(@PathVariable Integer id) {
        try {
            chapterService.deleteChapterById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping("/course/chapters/delete-chapters/{courseId}")
//    public ResponseEntity<HttpStatus> deleteAllCourseSChapter(@PathVariable Integer courseId) {
//        try {
//            chapterService.deleteAllCourseSChapter(courseId);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
