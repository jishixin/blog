package com.jishixin.blog.dao;/*
  User: 晨梦意志
  Date: 2019/9/5
  Time: 13:43
  Notes:
*/

import com.jishixin.blog.po.Blog;
import com.jishixin.blog.po.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    @Select("select * from t_comment where blog_id = #{blogId} order by create_time desc ")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "email",property = "email"),
            @Result(column = "content",property = "content"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "admin_comment",property = "adminComment"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "parent_comment_id",property = "parentComment",javaType = Comment.class,
                    one =@One(select = "com.jishixin.blog.dao.CommentDao.selectOne"))
    })
    List<Comment> findByBlogId(Long blogId);

    @Select("select * from t_comment where id = #{id}")
    Comment selectOne(Long id);

    @Insert("insert into t_comment(id,nickname,email,content,avatar,create_time,update_time," +
            "parent_comment_id,blog_id,admin_comment)" +
            " values (null,#{nickname}," +
            "#{email},#{content},#{avatar},#{createTime}," +
            "#{updateTime},#{parentComment.id},#{blog.id},#{adminComment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Comment comment);

    @Select("select * from t_comment where blog_id = #{blogId} and parent_comment_id is null order by create_time ")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "email",property = "email"),
            @Result(column = "content",property = "content"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "admin_comment",property = "adminComment"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "parent_comment_id",property = "parentComment",javaType = Comment.class,
                    one =@One(select = "com.jishixin.blog.dao.CommentDao.selectOne"))
    })
    List<Comment> findByBlogIdAndNoParent(Long blogId);

    @Select("select id,nickname,content,avatar,create_time,admin_comment from t_comment where parent_comment_id = #{id} order by create_time ")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "nickname",property = "nickname"),
            @Result(column = "content",property = "content"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "admin_comment",property = "adminComment")
    })
    List<Comment> findByParentId(Long id);
}
