package com.jishixin.blog.service;/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 10:30
  Notes:
*/

import com.jishixin.blog.po.Blog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> listBlog(Blog blog);

    List<Blog> listBlogs();
    List<Blog> listnewBlogs();

    List<Blog> listBlogsByTypeId(Long id);

    List<Blog> listBlogsByQuery(String query);

    List<Blog> listBlogByRecommend();

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);

    void addViews(Long id);

    List<Blog> listBlogsByTagId(List<Long> id);

    Map<String,List<Blog>> archiveBlog();

    Integer blogTotle();
}
