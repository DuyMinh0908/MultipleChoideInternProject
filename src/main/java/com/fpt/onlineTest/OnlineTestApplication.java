package com.fpt.onlineTest;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.model.Lesson;
import com.fpt.onlineTest.reponsitory.ChapterRepository;
import com.fpt.onlineTest.reponsitory.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineTestApplication {
//	private final LessonRepository lessonRepository;
//	private final ChapterRepository chapterRepository;
//
//	@Autowired
//	public OnlineTestApplication(LessonRepository lessonRepository, ChapterRepository chapterRepository) {
//		this.lessonRepository = lessonRepository;
//		this.chapterRepository = chapterRepository;
//		addFakeData();
//	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineTestApplication.class, args);
		//import fake data

	}
//	private void addFakeData() {
//		// Tạo một đối tượng Chapter trước
//		Chapter chapter = new Chapter();
//		chapter.setDescription("Chapter 1");
//		chapterRepository.save(chapter);
//		// Thêm dữ liệu vào bảng Lesson
//		for (int i = 1; i <= 3; i++) {
//			Lesson lesson = new Lesson();
//			lesson.setLessonTitle("Bài học " + i);
//			lesson.getChapter().setChapterId(chapter.getChapterId()); // Gán đối tượng Chapter cho Lesson
//
//			lessonRepository.save(lesson);
//		}
//	}
}
