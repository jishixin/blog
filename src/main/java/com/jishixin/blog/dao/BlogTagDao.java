package com.jishixin.blog.dao;/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 15:00
  Notes:
*/

import com.jishixin.blog.po.BlogTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogTagDao {

    @Insert("<script> insert into t_blog_tags(blogs_id,tags_id) values <foreach collection='blogTags' item='blogTag' separator=','>" +
            "        (#{blogTag.blogId},#{blogTag.tagId})" +
            "      </foreach></script>")
    void save(@Param("blogTags") List<BlogTag> blogTags);

    @Select("select tags_id from t_blog_tags where blogs_id = #{id}")
    List<Long> selectByBlogId(Long id);

    @Delete("delete from t_blog_tags where blogs_id=#{id}")
    void deleteByBlog(Long id);

    @Delete("delete from t_blog_tags where tags_id=#{id}")
    void deleteByTag(Long id);

    @Select("select blogs_id from t_blog_tags where tags_id = #{id}")
    List<Long> selectByTagId(Long id);

}
