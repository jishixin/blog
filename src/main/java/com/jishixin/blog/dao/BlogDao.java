package com.jishixin.blog.dao;/*
  User: 晨梦意志
  Date: 2019/9/4
  Time: 10:35
  Notes:
*/

import com.jishixin.blog.po.Blog;
import com.jishixin.blog.po.Type;
import com.jishixin.blog.po.User;
import com.jishixin.blog.provider.BlogProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    @Select("select * from t_blog where id=#{id}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "first_picture",property = "firstPicture"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "views",property = "views"),
            @Result(column = "appreciation",property = "appreciation"),
            @Result(column = "share_statement",property = "shareStatement"),
            @Result(column = "commentabled",property = "commentabled"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id",property = "type",javaType = Type.class,
                    one =@One(select = "com.jishixin.blog.dao.TypeDao.selectOne")),
            @Result(column = "user_id",property = "user",javaType = User.class,
                    one =@One(select = "com.jishixin.blog.dao.UserDao.findById"))
    })
    Blog selectOne(Long id);

    @Insert("insert into t_blog(id,title,content,first_picture,flag,views,appreciation," +
            "share_statement,commentabled,published,recommend,create_time,update_time,user_id,type_id)" +
            " values (null,#{title}," +
            "#{content},#{firstPicture},#{flag},#{views}," +
            "#{appreciation},#{shareStatement},#{commentabled}," +
            "#{published},#{recommend},#{createTime},#{updateTime}," +
            "#{user.id},#{type.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Blog blog);


    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "first_picture",property = "firstPicture"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "views",property = "views"),
            @Result(column = "appreciation",property = "appreciation"),
            @Result(column = "share_statement",property = "shareStatement"),
            @Result(column = "commentabled",property = "commentabled"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "description",property = "description"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id",property = "type",javaType = Type.class,
                    one =@One(select = "com.jishixin.blog.dao.TypeDao.selectOne"))
    })
    @SelectProvider(type = BlogProvider.class,method = "selectAll")
    List<Blog> selectAll(Blog blog);

    @Update("update t_blog SET title = #{title}, content=#{content} ," +
            "first_picture=#{firstPicture}, flag=#{flag}," +
            "appreciation=#{appreciation}, share_statement=#{shareStatement} , " +
            "commentabled=#{commentabled}, published=#{published}," +
            "recommend=#{recommend},create_time=#{createTime},update_time=#{updateTime},description=#{description}," +
            "user_id=#{user.id}, type_id=#{type.id}" +
            " WHERE id = #{id}")
    void update(Blog blog);

    @Delete("delete from t_blog where id=#{id}")
    void delete(Long id);

    @Select("select id,title from t_blog where recommend = 1 and published = 1 order by create_time desc")
    List<Blog> listBlogByRecommend();

    @Select("select id,title from t_blog where published = 1 and type_id = #{id} order by create_time desc")
    List<Blog> listBlogByType(Long id);

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "first_picture",property = "firstPicture"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "views",property = "views"),
            @Result(column = "appreciation",property = "appreciation"),
            @Result(column = "share_statement",property = "shareStatement"),
            @Result(column = "commentabled",property = "commentabled"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "description",property = "description"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id",property = "type",javaType = Type.class,
                    one =@One(select = "com.jishixin.blog.dao.TypeDao.selectOne")),
            @Result(column = "user_id",property = "user",javaType = User.class,
                    one =@One(select = "com.jishixin.blog.dao.UserDao.findById"))
    })
    @Select("select * from t_blog where published = 1 order by create_time desc")
    List<Blog> listBlogs();

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "first_picture",property = "firstPicture"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "views",property = "views"),
            @Result(column = "appreciation",property = "appreciation"),
            @Result(column = "share_statement",property = "shareStatement"),
            @Result(column = "commentabled",property = "commentabled"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "description",property = "description"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id",property = "type",javaType = Type.class,
                    one =@One(select = "com.jishixin.blog.dao.TypeDao.selectOne")),
            @Result(column = "user_id",property = "user",javaType = User.class,
                    one =@One(select = "com.jishixin.blog.dao.UserDao.findById"))
    })
    @SelectProvider(type = BlogProvider.class,method = "listBlogsByQuery")
    List<Blog> listBlogsByQuery(String query);

    @Update("update t_blog set views = views + 1 where id = #{id}")
    void addViews(Long id);

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "first_picture",property = "firstPicture"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "views",property = "views"),
            @Result(column = "appreciation",property = "appreciation"),
            @Result(column = "share_statement",property = "shareStatement"),
            @Result(column = "commentabled",property = "commentabled"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "description",property = "description"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id",property = "type",javaType = Type.class,
                    one =@One(select = "com.jishixin.blog.dao.TypeDao.selectOne")),
            @Result(column = "user_id",property = "user",javaType = User.class,
                    one =@One(select = "com.jishixin.blog.dao.UserDao.findById"))
    })
    @Select("select * from t_blog where published = 1 and type_id = #{id} order by create_time desc")
    List<Blog> listBlogsByTypeId(Long id);

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "first_picture",property = "firstPicture"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "views",property = "views"),
            @Result(column = "appreciation",property = "appreciation"),
            @Result(column = "share_statement",property = "shareStatement"),
            @Result(column = "commentabled",property = "commentabled"),
            @Result(column = "published",property = "published"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "description",property = "description"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
            @Result(column = "type_id",property = "type",javaType = Type.class,
                    one =@One(select = "com.jishixin.blog.dao.TypeDao.selectOne")),
            @Result(column = "user_id",property = "user",javaType = User.class,
                    one =@One(select = "com.jishixin.blog.dao.UserDao.findById"))
    })
    @Select("<script> select * from t_blog where id in " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>#{id} </foreach>" +
            "</script>")
    List<Blog> listBlogsByTagId(@Param("ids") List<Long> ids);

    @Select("<script> select date_format(b.update_time,'%Y') as year from t_blog b group by year order by year desc </script>")
    List<String> findGroupYears();

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "flag",property = "flag"),
            @Result(column = "update_time",property = "updateTime"),
    })
    @Select("<script>select b.id,b.title,b.flag,b.update_time from t_blog b where date_format(b.update_time,'%Y') = #{year}</script>")
    List<Blog> findByYear(@Param("year") String year);

    @Select("select count(*) from t_blog")
    Integer blogTotle();

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "title",property = "title"),
    })
    @Select("select id,title from t_blog where published = 1 order by create_time desc")
    List<Blog> listnewBlogs();
}
