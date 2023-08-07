package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.BlogRepository;
import com.fpt.onlineTest.reponsitory.UserRepository;
import com.fpt.onlineTest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    //    create new blog
    @Override
    public Blog newBLog(Blog blog) {
        User user = userRepository.findById(blog.getUser().getUserId()).orElse(null);
        if (user == null)
            return null;
        else
            blog.setUser(user);
        return blogRepository.save(blog);
    }

    //    get all blogs
    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    //    get blog by id
    @Override
    public Optional<Blog> getBlogById(Integer blogId) {
        return blogRepository.findById(blogId);
    }

    //    get all blog by userId
    public List<Blog> getAllBlogsByUserId(Integer userId) {
        return blogRepository.findAllBlogsByUserId(userId);
    }

    //    update blog
    @Override
    public Blog updateBlog(Integer blogId, Blog blog) {
        Blog updatedBlog = new Blog();
        updatedBlog.setContentBlog(blog.getContentBlog());
        updatedBlog.setTitleBlog(blog.getTitleBlog());
        return blogRepository.save(updatedBlog);
    }

    //    delete blog by blog id
    @Override
    public void deleteBlogById(Integer blogId) {
        blogRepository.deleteById(blogId);
    }

    //    delete all blogs by userId
    @Override
    public void deleteAllBlogsByUserId(Integer userId) {
        blogRepository.deleteAllByUserId(userId);
    }
//    get top 2 blogs have most visitors
    public List<Blog> getTop2Blogs(){
        List<Blog> top2Blogs = blogRepository.findTop2Blogs();
        return top2Blogs.subList(0, Math.min(5, top2Blogs.size()));
    }

    public Long getUserId(Blog blog) {
        return Long.valueOf(blog.getUser().getUserId());
    }
}
