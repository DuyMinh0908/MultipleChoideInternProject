package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);

}
