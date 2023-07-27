package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.reponsitory.ChapterRepository;
import com.fpt.onlineTest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

//    create chapter
    @PostMapping("/course/chapters/create")
    public ResponseEntity<Chapter> createNewChapter(@RequestBody Chapter newChapter){
        try {
            return new ResponseEntity<>(chapterService.createChapter(newChapter), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
