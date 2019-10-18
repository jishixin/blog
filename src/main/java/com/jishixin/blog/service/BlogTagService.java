package com.jishixin.blog.service;/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 14:58
  Notes:
*/

import com.jishixin.blog.po.BlogTag;

import java.util.List;

public interface BlogTagService {

    void save(Long blogId,String tagsIds);

    List<Long> selectByBlogId(Long id);

    void deleteByBlog(Long id);

    void deleteByTag(Long id);

    List<Long> selectByTagId(Long id);

}
