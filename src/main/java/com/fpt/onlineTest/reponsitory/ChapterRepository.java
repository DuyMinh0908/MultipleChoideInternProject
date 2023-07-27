package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter,Integer> {
}
