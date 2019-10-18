package com.jishixin.blog.service;

import com.jishixin.blog.dao.BlogDao;
import com.jishixin.blog.dao.BlogTagDao;
import com.jishixin.blog.po.Blog;
import com.jishixin.blog.util.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 10:34
  Notes:
*/
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.selectOne(id);
    }

    @Override
    public List<Blog> listBlog(Blog blog) {
        return blogDao.selectAll(blog);
    }

    @Override
    public List<Blog> listBlogs() {
        return blogDao.listBlogs();
    }

    @Override
    public List<Blog> listnewBlogs() {
        return blogDao.listnewBlogs();
    }

    @Override
    public List<Blog> listBlogsByTypeId(Long id) {
        return blogDao.listBlogsByTypeId(id);
    }

    @Override
    public List<Blog> listBlogsByQuery(String query) {
        return blogDao.listBlogsByQuery(query);
    }

    @Override
    public List<Blog> listBlogByRecommend() {
        return blogDao.listBlogByRecommend();
    }

    @Override
    public void saveBlog(Blog blog) {
        blogDao.save(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        Blog b = blogDao.selectOne(blog.getId());
        if (b==null){
            throw new NotFoundException("该博客不存在!");
        }
        blogDao.update(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.delete(id);
    }

    @Override
    public void addViews(Long id) {
        blogDao.addViews(id);
    }

    @Override
    public List<Blog> listBlogsByTagId(List<Long> blogsId) {
        return blogDao.listBlogsByTagId(blogsId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogDao.findGroupYears();
        Map<String, List<Blog>> map = new HashMap<>();
        for(String year:years){
            map.put(year,blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public Integer blogTotle() {
        return blogDao.blogTotle();
    }
}
