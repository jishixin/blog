package com.jishixin.blog.service;

import com.jishixin.blog.dao.BlogTagDao;
import com.jishixin.blog.po.BlogTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 15:00
  Notes:
*/

@Service
@Transactional
public class BlogTagServiceImpl implements  BlogTagService{

    @Autowired
    private BlogTagDao blogTagDao;

    @Override
    public void save(Long blogId,String tagsIds) {
        /*String[] ids = tagsIds.split(",");
        String sql="";
        for (int i=0;i<ids.length;i++){
            sql+="("+blogId+","+ids[i]+"),";
        }
        sql=sql.substring(0,sql.length()-1);*/
        System.out.println(tagsIds);
        List<BlogTag> blogTags=new ArrayList<>();
        String[] ids = tagsIds.split(",");
        for (int i =0;i<ids.length;i++){
            blogTags.add(new BlogTag(blogId,Long.valueOf(ids[i])));
        }
        blogTagDao.save(blogTags);
    }

    @Override
    public List<Long> selectByBlogId(Long id) {
        return blogTagDao.selectByBlogId(id);
    }

    @Override
    public void deleteByBlog(Long id) {

    }

    @Override
    public void deleteByTag(Long id) {

    }

    @Override
    public List<Long> selectByTagId(Long id) {
        return blogTagDao.selectByTagId(id);
    }
}
