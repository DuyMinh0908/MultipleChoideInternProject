package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Address;
import com.fpt.onlineTest.model.Person;
import com.fpt.onlineTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReponsitory extends JpaRepository< User,Integer> {

}
