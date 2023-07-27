package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    // create blog
    Blog newBLog(Blog blog);

    //    get all blogs
    List<Blog> getAll();

    // get blog by id
    Optional<Blog> getBlogById(Integer blogId);

//    get popular blog


    //    get all blog by userId
    List<Blog> getAllBlogsByUserId( Integer userId);

    //    update blog
    Blog updateBlog(Integer id, Blog blog);

    //  delete blog by id
    void deleteBlogById(Integer blogId);

    //    delete all user blogs by userId
    void deleteAllBlogsByUserId(Integer userId);
//    get top 5 blog have most visitors
    List<Blog> getTop2Blogs();
}
