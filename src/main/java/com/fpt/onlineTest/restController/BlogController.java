package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class BlogController {
    @Autowired
    private BlogService blogService;

    //    create blog
    @PostMapping("/blogs/add")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog newBlog) {
        try {
            return new ResponseEntity<>(blogService.newBLog(newBlog), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get all blogs
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        try {
            return new ResponseEntity<>(blogService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    get blog by id
    @GetMapping("/blogs/blogId{id}")
    public ResponseEntity<Optional<Blog>> getBlogById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(blogService.getBlogById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    get all blog by userId
    @GetMapping("/blogs/user-blogs/userId:{userId}")
    public ResponseEntity<List<Blog>> getAllBlogsByUserId(@PathVariable Integer userId) {
        List<Blog> blogsWithUserId = blogService.getAllBlogsByUserId(userId);
        try {
            return new ResponseEntity<>(blogsWithUserId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //    update blog
    @PutMapping("/blogs/user-blog/update/blogId{blogId}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Integer blogId, Blog newBlog) {
        try {
            return new ResponseEntity<>(blogService.updateBlog(blogId, newBlog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    delete blog by blog id
    @DeleteMapping("/blogs/user-blogs/delete/{blogId}")
    public ResponseEntity<HttpStatus> deleteBlogById(@PathVariable Integer blogId) {
        blogService.deleteBlogById(blogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    delete all blogs by userId
    @DeleteMapping("/blogs/user-blogs/delete-by/userId:{userId}")
    public ResponseEntity<HttpStatus> deleteBlogsByUserId(@PathVariable Integer userId) {
        blogService.deleteAllBlogsByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    get top blogs have most visitors
    @GetMapping("/blogs/popular-blogs")
    public ResponseEntity<List<Blog>> getPopularBlogs(){
        try {
            return new ResponseEntity<>(blogService.getTop2Blogs(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
