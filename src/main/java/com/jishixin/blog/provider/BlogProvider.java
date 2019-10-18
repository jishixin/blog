package com.jishixin.blog.provider;

import com.jishixin.blog.po.Blog;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 10:58
  Notes:
*/
public class BlogProvider {
    public String selectAll(Blog blog){
        return new SQL(){
            {
                SELECT(" * ");
                FROM("t_blog");
                if (blog!=null){
                    if (blog.getTitle()!=null && blog.getTitle()!=""){
                        WHERE("title like CONCAT('%',#{title},'%')");
                    }
                    if (blog.getType().getId()!=null){
                        WHERE("type_id = #{type.id}");
                    }
                }
                ORDER_BY("update_time desc");
            }
        }.toString();
    }

    public String listBlogsByQuery(String query){
        return new SQL(){
            {
                SELECT(" * ");
                FROM("t_blog");
                WHERE("published = 1");
                if (query!=null&&query!="")
                WHERE("title like CONCAT('%',#{query},'%')");
                ORDER_BY("create_time desc");
            }
        }.toString();
    }
}
