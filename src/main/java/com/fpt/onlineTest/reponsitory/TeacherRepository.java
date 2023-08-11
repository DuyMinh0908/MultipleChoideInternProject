package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
//    Teacher findByUsername(String username);

}
