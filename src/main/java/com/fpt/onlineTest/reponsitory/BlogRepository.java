package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @Query(value = "SELECT b FROM Blog b ORDER BY b.numberVisitors DESC ")
    List<Blog> findTop2Blogs();

    @Query("SELECT b FROM Blog b WHERE b.user.userId = :userId")
    List<Blog> findAllBlogsByUserId(Integer userId);

    @Query("DELETE FROM Blog b WHERE b.user.userId= :userId")
    void deleteAllByUserId(Integer userId);
}
